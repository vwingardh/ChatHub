package com.chat.chat.message;

import com.chat.chat.customExceptions.MessageExceedsMaximumException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public Message createMessage(String text) throws MessageExceedsMaximumException {
        if (text.length() >= 501) {
            throw new MessageExceedsMaximumException();
        }
        Message message = new Message();
        message.setMessageText(text);
        return repo.save(message);
    }

    public void deleteMessageById(Long id) throws NoSuchElementException {
        Optional<Message> message = repo.findById(id);
        if (!message.isPresent()) {
            throw new NoSuchElementException(String.format("Message with id '%s' does not exist", id));
        }
        repo.deleteById(id);
    }
}
