package com.chat.chat.user;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_user")
public class ChatUser {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    protected ChatUser() {

    }

    public ChatUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
