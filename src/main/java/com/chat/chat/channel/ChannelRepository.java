package com.chat.chat.channel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Channel findByChannelName(String channelName);
    Channel findByLink(String joinLink);
}
