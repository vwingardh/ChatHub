package com.chat.chat.message;

import com.chat.chat.chatroom.Chatroom;
import com.chat.chat.user.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessageBySenderAndChatroom(ChatUser sender, Chatroom chatroom);
    List<Message> findMessageByChatroom(Chatroom chatroom);
}
