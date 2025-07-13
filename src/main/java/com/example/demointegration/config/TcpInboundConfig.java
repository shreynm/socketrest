package com.example.demointegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import com.example.demointegration.service.HeaderValidator;

@Configuration
public class TcpInboundConfig {

    private final HeaderValidator headerValidator;

    TcpInboundConfig(HeaderValidator headerValidator) {
        this.headerValidator = headerValidator;
    }

    @Bean
    public TcpInboundGateway inboundTcpAdapter(AbstractServerConnectionFactory serverConnectionFactory) {
        TcpInboundGateway adapter = new TcpInboundGateway();
        adapter.setConnectionFactory(serverConnectionFactory);
        adapter.setRequestChannelName("fromTcp");
        adapter.setReplyChannelName("toTcp");
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "toTcp")
    public MessageHandler tcpOutGate(AbstractClientConnectionFactory connectionFactory) {
        TcpOutboundGateway gate = new TcpOutboundGateway();
        gate.setConnectionFactory(connectionFactory);
        return gate;
    }

    @ServiceActivator(inputChannel = "fromTcp", outputChannel = "routerDecision")
    public Message upCase(Message<?> message) {
        boolean isResponseEntity = (message.getPayload() instanceof ResponseEntity);
        if (isResponseEntity) {
            return MessageBuilder.fromMessage(message).setHeader("route", "barChannel").build();
        }
        return MessageBuilder.fromMessage(message).setHeader("route", "fooChannel").build();
    }

    @Bean
    public AbstractServerConnectionFactory serverCF() {
        return new TcpNetServerConnectionFactory(1234);
    }

    @Bean
    public IntegrationFlow routerFlow() {
        return IntegrationFlow.from("routerDecision")
                .route(Message.class,
                        p -> p.getHeaders().get("route", String.class).equals("fooChannel") ? "fooChannel"
                                : "barChannel")
                .get();
    }

    @ServiceActivator(inputChannel = "fooChannel")
    public String getMessString(Message m) {
        return headerValidator.getData().getBody().toString();
    }

    @ServiceActivator(inputChannel = "barChannel")
    public Object getMessageString(Message m) {
        System.err.println("I am in barChannel");
        return m.getPayload();
    }

    @Bean
    public AbstractClientConnectionFactory clientCF() {
        return new TcpNetClientConnectionFactory("localhost", 1234);
    }

}