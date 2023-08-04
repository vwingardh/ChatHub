package com.chat.chat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository chatRepo;

    @Autowired
    public UserService(UserRepository chatRepo) {
        this.chatRepo = chatRepo;
    }

    public ChatUser createUser(String username) {
        return chatRepo.createUser(username);
    }
}
