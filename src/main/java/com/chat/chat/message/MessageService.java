package com.chat.chat.message;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }
}
