package com.example.batchrabbitamqphome.config;

import com.example.batchrabbitamqphome.domain.MessageTemplate;
import com.example.batchrabbitamqphome.model.Message;
//import com.producer.rabbit.domain.MessageTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.batch.item.amqp.builder.AmqpItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public final RabbitTemplate rabbitTemplate;

    public BatchConfiguration(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public AmqpItemReader<MessageTemplate> reader(){
        return new AmqpItemReaderBuilder<MessageTemplate>()
                .amqpTemplate(rabbitTemplate)
//                .itemType(String.class)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<MessageTemplate> reader,
                   ItemProcessor<MessageTemplate, Message> processor,
                   ItemWriter<Message> writer) {

        Step step = stepBuilderFactory.get("receiveMessageAndSendAnotherService")
                .<MessageTemplate, Message>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

        return jobBuilderFactory.get("receiveMessage")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}
