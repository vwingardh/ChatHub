package com.chat.chat.message;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_text")
    private String messageText;

    public Message() {

    }

    public Message(Long id, String messageText) {
        this.id = id;
        this.messageText = messageText;
    }

    public Long getId() {
        return id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

}
