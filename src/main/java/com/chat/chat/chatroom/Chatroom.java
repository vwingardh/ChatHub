package com.chat.chat.chatroom;

import jakarta.persistence.*;

@Entity
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chatroom_name", nullable = false)
    private String chatroomName;

    public Chatroom() {

    }

    public Chatroom(Long id, String name) {
        this.id = id;
        this.chatroomName = name;
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
}
