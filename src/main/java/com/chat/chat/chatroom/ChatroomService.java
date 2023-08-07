package com.chat.chat.chatroom;

import com.chat.chat.customExceptions.ChatroomNameAlreadyTakenException;
import com.chat.chat.customExceptions.InvalidChatroomNameLengthException;
import com.chat.chat.user.ChatUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Transactional
public class ChatroomService {

    private final ChatroomRepository repo;

    @Autowired
    public ChatroomService(ChatroomRepository repo) {
        this.repo = repo;
    }

    public Chatroom createChatroom(String chatroomName) throws ChatroomNameAlreadyTakenException {
        if (chatroomName.length() <= 4 || chatroomName.length() >= 31) {
            throw new InvalidChatroomNameLengthException(chatroomName.length());
        }
        Chatroom isChatroomAvailable = repo.findByChatroomName(chatroomName);
        if (isChatroomAvailable != null) {
            throw new ChatroomNameAlreadyTakenException(chatroomName);
        }
        Chatroom chatroom = new Chatroom();
        chatroom.setName(chatroomName);
        return repo.save(chatroom);
    }

    public void deleteChatroomById(Long id) {
        Optional<Chatroom> chatroom = repo.findById(id);
        if (!chatroom.isPresent()) {
            throw new NoSuchElementException(String.format("Chatroom with id '%s' does not exist", id));
        }
        repo.deleteById(id);
    }

    public Chatroom getChatroomByChatroomName(String chatroomName) throws NoSuchElementException {
        Chatroom chatroom = repo.findByChatroomName(chatroomName);
        if (chatroom == null) {
            throw new NoSuchElementException(String.format("Chatroom with name '%s' does not exist", chatroomName));
        }
        return chatroom;
    }

    public Chatroom getChatroomById(Long id) throws NoSuchElementException {
        Optional<Chatroom> chatroom = repo.findById(id);
        if (!chatroom.isPresent()) {
            throw new NoSuchElementException(String.format("Chatroom with id '%s' does not exist", id));
        }
        return chatroom.get();
    }

}

