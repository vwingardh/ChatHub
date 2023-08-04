package com.chat.chat.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepo;

    @Autowired
    public ChatService(ChatRepository chatRepo) {
        this.chatRepo = chatRepo;
    }

    public ChatUser createUser(String username) {
        return chatRepo.createUser(username);
    }
}
