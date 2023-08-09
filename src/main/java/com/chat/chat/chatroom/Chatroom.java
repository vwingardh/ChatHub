package com.chat.chat.chatroom;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chatroom_name", nullable = false)
    private String chatroomName;

    private String link;

    public Chatroom() {

    }

    public Chatroom(Long id, String name) {
        this.id = id;
        this.chatroomName = name;
    }

    @PrePersist
    public void generateUuidLink() {
        if (link == null) {
            link = UUID.randomUUID().toString();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return chatroomName;
    }

    public void setName(String name) {
        this.chatroomName = name;
    }

    public String getLink() {
        return link;
    }
}
