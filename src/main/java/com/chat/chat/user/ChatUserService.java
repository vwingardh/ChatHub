package com.chat.chat.user;

import com.chat.chat.customExceptions.InvalidUsernameLengthException;
import com.chat.chat.customExceptions.UsernameAlreadyTakenException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class ChatUserService {

    private final ChatUserRepository repo;

    @Autowired
    public ChatUserService(ChatUserRepository repo) {
        this.repo = repo;
    }

    public ChatUser getUserByUsername(String username) throws NoSuchElementException {
        return findUserByUsernameOrElseThrow(username);
    }

    public ChatUser createUser(String username) throws UsernameAlreadyTakenException {
        if (username.length() <= 4 || username.length() >= 31) {
            throw new InvalidUsernameLengthException(username.length());
        }
        isUsernameAvailableElseThrow(username);

        ChatUser user = new ChatUser();
        user.setUsername(username.trim());
        return repo.save(user);
    }

    public ChatUser getUserById(Long userId) throws NoSuchElementException {
        return findUserByIdOrElseThrow(userId);
    }

    public void deleteUserById(Long userId) throws NoSuchElementException {
        ChatUser user = findUserByIdOrElseThrow(userId);
        repo.deleteById(user.getId());
    }

    public List<ChatUser> getAllUsers() {
        return repo.findAll();
    }

    private ChatUser findUserByUsernameOrElseThrow(String username) {
        ChatUser user = repo.findByUsername(username);
        if (user == null) {
            throw new NoSuchElementException(String.format("User with username '%s' does not exist", username));
        }
        return user;
    }

    private void isUsernameAvailableElseThrow(String username) {
        ChatUser isUsernameAvailable = repo.findByUsername(username);
        if (isUsernameAvailable != null) {
            throw new UsernameAlreadyTakenException(username);
        }
    }

    private ChatUser findUserByIdOrElseThrow(Long userId) {
        return repo.findById(userId).orElseThrow(() ->
                new NoSuchElementException(String.format("User with id '%s' does not exist", userId)));
    }

}
