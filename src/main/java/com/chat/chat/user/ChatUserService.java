package com.chat.chat.user;

import com.chat.chat.customExceptions.InvalidUsernameLengthException;
import com.chat.chat.customExceptions.UsernameAlreadyTakenException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ChatUserService {

    private final ChatUserRepository repo;

    @Autowired
    public ChatUserService(ChatUserRepository repo) {
        this.repo = repo;
    }

    public ChatUser getUserByUsername(String username) throws NoSuchElementException {
        ChatUser user = repo.findByUsername(username);
        if (user == null) {
            throw new NoSuchElementException(String.format("User with username '%s' does not exist", username));
        }
        return user;
    }

    public ChatUser createUser(String username) throws UsernameAlreadyTakenException {
        if (username.length() <= 4 || username.length() >= 31) {
            throw new InvalidUsernameLengthException(username.length());
        }
        ChatUser isUsernameAvailable = repo.findByUsername(username);
        if (isUsernameAvailable != null) {
            throw new UsernameAlreadyTakenException(username);
        }
        ChatUser user = new ChatUser();
        user.setUsername(username);
        return repo.save(user);
    }

    public ChatUser getUserById(Long id) throws NoSuchElementException {
        Optional<ChatUser> chatUser = repo.findById(id);
        if (!chatUser.isPresent()) {
            throw new NoSuchElementException(String.format("User with id '%s' does not exist", id));
        }
        return chatUser.get();
    }

    public void deleteUserById(Long id) throws NoSuchElementException {
        Optional<ChatUser> user = repo.findById(id);
        if (!user.isPresent()) {
            throw new NoSuchElementException(String.format("User with id '%s' does not exist", id));
        }
        repo.deleteById(id);
    }

    public List<ChatUser> getAllUsers() {
        return repo.findAll();
    }

}
