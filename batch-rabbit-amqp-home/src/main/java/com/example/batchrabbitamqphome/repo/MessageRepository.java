package com.example.batchrabbitamqphome.repo;

import com.example.batchrabbitamqphome.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
