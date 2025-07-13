package com.example.demointegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericHandler;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.integration.http.dsl.Http;

@SpringBootApplication
public class DemointegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemointegrationApplication.class, args);
	}

	//@Configuration
	public class HttpInboundGatewayExample {

		/*
		 * @Bean
		 * public TcpInboundGateway tcpInGate(AbstractServerConnectionFactory
		 * connectionFactory) {
		 * TcpInboundGateway inGate = new TcpInboundGateway();
		 * inGate.setConnectionFactory(connectionFactory);
		 * inGate.setRequestChannel(fromTcp());
		 * return inGate;
		 * }
		 

		@Bean
		public TcpReceivingChannelAdapter inboundTcpAdapter(AbstractServerConnectionFactory serverConnectionFactory) {
			TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
			adapter.setConnectionFactory(serverConnectionFactory);
			adapter.setOutputChannelName("fromTcp");
			return adapter;
		}

		// @Bean
		// public MessageChannel fromTcp1234() {
		// 	return new DirectChannel();
		// }

		@Transformer(inputChannel = "fromTcp", outputChannel = "toEcho")
		public String convert(byte[] bytes) {
			return new String(bytes);
		}

		// @ServiceActivator(inputChannel = "toEcho")
		// public String upCase(String in) {
		// 	System.err.println(in);
		// 	return in;
		// }

		@Bean
		public AbstractServerConnectionFactory serverCF() {
			return new TcpNetServerConnectionFactory(1234);
		}*/
	}
}
