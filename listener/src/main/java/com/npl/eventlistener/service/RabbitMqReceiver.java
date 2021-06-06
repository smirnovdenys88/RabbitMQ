package com.npl.eventlistener.service;

import com.npl.eventlistener.domain.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceiver {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage1(MessageTemplate messageTemplate) {
        logger.info("Received is.. " + messageTemplate);
        System.out.println(messageTemplate);
    }

//    @RabbitListener(queues = "${spring.rabbitmq.queue}")
//    public void receivedMessage1(String messageTemplate) {
//        logger.info("Received is.. " + messageTemplate);
//        System.out.println(messageTemplate);
//    }
}
