package com.producer.rabbit.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = MessageTemplate.class)
public class MessageTemplate {
    private String Id;
    private String data;

    public MessageTemplate() {
    }

    public MessageTemplate(String id, String data) {
        Id = id;
        this.data = data;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
