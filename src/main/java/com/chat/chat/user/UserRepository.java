package com.chat.chat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final JpaRepository repo;

    @Autowired
    public UserRepository(JpaRepository repo) {
        this.repo = repo;
    }

    public ChatUser createUser(String username) {
        ChatUser user = new ChatUser();
        user.setUsername(username);
        repo.save(user);
        return user;
    }

    public Optional<ChatUser> findById(Long id) {
        return repo.findById(id);
    }
}
