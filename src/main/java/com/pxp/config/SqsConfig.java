// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;

import java.util.Collections;

@Configuration
public class SqsConfig {

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory(final ObjectMapper mapper,
                                                                 final AmazonSQSAsync amazonSQSAsync) {
        final QueueMessageHandlerFactory queueHandlerFactory = new QueueMessageHandlerFactory();
        queueHandlerFactory.setAmazonSqs(amazonSQSAsync);
        queueHandlerFactory.setArgumentResolvers(Collections.<HandlerMethodArgumentResolver>singletonList(
                new PayloadMethodArgumentResolver(jackson2MessageConverter(mapper))));

        return queueHandlerFactory;
    }

    private MessageConverter jackson2MessageConverter(final ObjectMapper mapper) {
        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setStrictContentTypeMatch(false);
        converter.setObjectMapper(mapper);

        return converter;
    }
}
