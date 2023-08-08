package com.chat.chat.message;

import com.chat.chat.chatroom.Chatroom;
import com.chat.chat.user.ChatUser;
import jakarta.persistence.*;
import java.time.ZonedDateTime;


@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_text", nullable = false, length = 500)
    private String messageText;

    private ZonedDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ChatUser sender;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    public Message() {

    }

    public Message(String messageText, ChatUser sender, Chatroom chatroom) {
        this.messageText = messageText;
        this.sender = sender;
        this.chatroom = chatroom;
    }

    @PrePersist
    public void setDateTimeCreated() {
        this.created = ZonedDateTime.now();
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

    public ZonedDateTime getCreated() {
        return created;
    }

    public ChatUser getSender() {
        return sender;
    }

    public void setSender(ChatUser sender) {
        this.sender = sender;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }
}
