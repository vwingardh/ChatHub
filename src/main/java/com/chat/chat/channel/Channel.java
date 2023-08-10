package com.chat.chat.channel;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channel_name", nullable = false)
    private String channelName;

    @Column(name = "channel_link")
    private String link;

    public Channel() {

    }

    public Channel(Long id, String channelName) {
        this.id = id;
        this.channelName = channelName;
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
        return channelName;
    }

    public void setName(String channelName) {
        this.channelName = channelName;
    }

    public String getLink() {
        return link;
    }
}
