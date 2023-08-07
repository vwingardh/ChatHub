package com.chat.chat.chatroom;

import com.chat.chat.user.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

    Chatroom findByName(String chatroomName);
}
