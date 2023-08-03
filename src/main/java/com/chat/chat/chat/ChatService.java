package com.chat.chat.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public ChatUser createUser() {
        ChatUser user = new ChatUser(1, "vileshocka");
        return user;
    }
}
