package com.chat.chat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatUserService {

    private final ChatUserRepository repo;

    @Autowired
    public ChatUserService(ChatUserRepository repo) {
        this.repo = repo;
    }

    public ChatUser createUser(String username) {
        ChatUser user = new ChatUser();
        user.setUsername(username);
        repo.save(user);
        return user;
    }

    public List<ChatUser> getAllUsers() {
        return repo.findAll();
    }
}
