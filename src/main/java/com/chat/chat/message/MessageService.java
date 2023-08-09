package com.chat.chat.message;

import com.chat.chat.chatroom.Chatroom;
import com.chat.chat.chatroom.ChatroomRepository;
import com.chat.chat.customExceptions.MessageExceedsMaximumException;
import com.chat.chat.user.ChatUser;
import com.chat.chat.user.ChatUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepo;
    private final ChatroomRepository chatRepo;
    private final ChatUserRepository userRepo;

    @Autowired
    public MessageService(MessageRepository messageRepo, ChatroomRepository chatRepo, ChatUserRepository userRepo) {
        this.messageRepo = messageRepo;
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
    }

    public Message createMessage(String text, Long userId, Long chatroomId) throws MessageExceedsMaximumException {
        if (text.length() >= 501) {
            throw new MessageExceedsMaximumException();
        }
        Optional<ChatUser> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new NoSuchElementException(String.format("User with id '%s' does not exist", userId));
        }
        Optional<Chatroom> chatroom = chatRepo.findById(chatroomId);
        if (!chatroom.isPresent()) {
            throw new NoSuchElementException(String.format("Chatroom with id '%s' does not exist", chatroomId));
        }
        Message message = new Message();
        message.setMessageText(text);
        message.setChatroom(chatRepo.findById(chatroomId).get());
        message.setSender(userRepo.findById(userId).get());
        return messageRepo.save(message);
    }

    public void deleteMessageById(Long id) throws NoSuchElementException {
        Optional<Message> message = messageRepo.findById(id);
        if (!message.isPresent()) {
            throw new NoSuchElementException(String.format("Message with id '%s' does not exist", id));
        }
        messageRepo.deleteById(id);
    }

    public Message getMessageById(Long id) throws NoSuchElementException {
        Optional<Message> message = messageRepo.findById(id);
        if (!message.isPresent()) {
            throw new NoSuchElementException(String.format("Message with id '%s' does not exist", id));
        }
        return message.get();
    }

    public List<Message> getMessagesByUserIdChatroomId(Long userId, Long chatroomId) throws NoSuchElementException {
        Optional<ChatUser> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new NoSuchElementException(String.format("User with id '%s' does not exist", userId));
        }
        Optional<Chatroom> chatroom = chatRepo.findById(chatroomId);
        if (!chatroom.isPresent()) {
            throw new NoSuchElementException(String.format("Chatroom with id '%s' does not exist", chatroomId));
        }
        List<Message> messages = messageRepo.findMessageBySenderAndChatroom(user.get(), chatroom.get());
        return messages;
    }

}
