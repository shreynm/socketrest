package com.example.demointegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.example.demointegration.service.HeaderValidator;

@Configuration
public class HttpInboundConfiguration {

    @Bean
    public IntegrationFlow inboundHttpFlow(HeaderValidator headerValidator) {
        return IntegrationFlow.from(Http.inboundGateway("/greet")
                .requestMapping(m -> m.methods(HttpMethod.GET))
                .requestPayloadType(String.class))
                .handle((p, h) -> {
                    return MessageBuilder
                            .withPayload(headerValidator.getData())
                            .setHeader("custom", "custom")
                            .build();
                })
                .handle((p, h) -> {
                    return MessageBuilder.withPayload(p).build();
                })
                .channel("fromTcp")
                .get();
    }

    /*
     * @ServiceActivator(inputChannel = "toEcho")
     * public String upCase(Message message) {
     * System.err.println(message.getPayload());
     * System.err.println(message);
     * return message.getPayload().toString().toUpperCase();
     * }
     */

}
