package com.chat.chat.message;

import com.chat.chat.channel.Channel;
import com.chat.chat.user.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessageBySenderAndChannel(ChatUser sender, Channel channel);
    List<Message> findMessageByChannel(Channel channel);
}
