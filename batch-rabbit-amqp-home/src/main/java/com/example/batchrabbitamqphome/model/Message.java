package com.example.batchrabbitamqphome.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message {
    @Id
    private long id;
    private String firstName;
    private Date date;

    public Message() {
    }

    public Message(long id, String firstName, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", date=" + date +
                '}';
    }
}
