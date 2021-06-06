package com.example.batchrabbitamqphome.service;

import com.example.batchrabbitamqphome.domain.MessageTemplate;
import com.example.batchrabbitamqphome.model.Message;
//import com.producer.rabbit.domain.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Processor implements ItemProcessor<MessageTemplate, Message> {
    private static final Logger logger = LoggerFactory.getLogger(Processor.class);

    private final AtomicLong atomicLong = new AtomicLong(1);

    @Override
    public Message process(MessageTemplate messageTemplate) {
        Message message = new Message();
        message.setId(atomicLong.incrementAndGet());
        message.setFirstName(messageTemplate.getData());
        message.setDate(new Date(System.currentTimeMillis()));

        logger.info("Processor processing ... " + message);
        return message;
    }
}
