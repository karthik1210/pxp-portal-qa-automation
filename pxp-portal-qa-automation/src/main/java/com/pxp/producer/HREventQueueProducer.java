// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.producer;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.pxp.model.BaseRest;
import groovy.util.logging.Log4j2;
import io.awspring.cloud.messaging.core.QueueMessageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class HREventQueueProducer extends BaseRest {

    @Autowired
    private final AmazonSQSAsync amazonSqs;

  /*  @Value("${hr.event.queue.url}")
    private String hrEventQueueUrl;

    @Value("${hr.event.queue.waittimeout}")
    private Long waitTimeoutMillis;*/

    @Autowired
    public HREventQueueProducer(final AmazonSQSAsync amazonSQSAsync) {
        this.amazonSqs = amazonSQSAsync;
    }

    public boolean send(final String hrEventMsg) {
        MessageChannel messageChannel = new QueueMessageChannel(amazonSqs, testData.getProperty("awsqueueurl"));

        Message<String> msg = MessageBuilder.withPayload(hrEventMsg)
                .setHeader("sender", "pp-hr-event-Service")
                .build();

        boolean sentStatus = messageChannel.send(msg, 5000);

        System.out.println("Message sent Successfully : {}"+hrEventMsg);

        return sentStatus;
    }

}
