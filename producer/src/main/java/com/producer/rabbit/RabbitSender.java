package com.producer.rabbit;

import com.producer.rabbit.domain.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EnableScheduling
public class RabbitSender {
    private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    private RabbitTemplate rabbitTemplate;

//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${spring.rabbitmq.routingKey}")
//    private String routingKey;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 2000)
    @Async
    public void scheduleFixedRateTaskFast() {
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setId(UUID.randomUUID().toString());
        messageTemplate.setData(UUID.randomUUID().toString());

        sendToRabbitmqCustomObject(messageTemplate);
//        sendToRabbitmqString(UUID.randomUUID().toString());
    }

    public void sendToRabbitmqCustomObject(MessageTemplate messageTemplate) {
//        rabbitTemplate.convertAndSend(exchange, routingKey, messageTemplate);
        rabbitTemplate.convertAndSend(queue, messageTemplate);
//        rabbitTemplate.convertAndSend(messageTemplate);
        logger.info("Send msg = " + messageTemplate);
    }

    public void sendToRabbitmqString(String str) {
//        rabbitTemplate.convertAndSend(exchange, routingKey, str);
//        rabbitTemplate.convertAndSend(messageTemplate);
        logger.info("Send msg = " + str);
    }
}
