package com.chat.chat.message;

import com.chat.chat.channel.Channel;
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
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public Message() {

    }

    public Message(String messageText, ChatUser sender, Channel channel) {
        this.messageText = messageText;
        this.sender = sender;
        this.channel = channel;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
