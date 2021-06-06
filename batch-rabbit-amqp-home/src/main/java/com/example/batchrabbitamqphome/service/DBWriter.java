package com.example.batchrabbitamqphome.service;

import com.example.batchrabbitamqphome.model.Message;
import com.example.batchrabbitamqphome.repo.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<Message> {
    private static final Logger logger = LoggerFactory.getLogger(DBWriter.class);

    public final MessageRepository messageRepository;

    public DBWriter(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void write(List<? extends Message> list) throws Exception {
        logger.info("Saving ..." + list);
        messageRepository.saveAll(list);
    }
}
