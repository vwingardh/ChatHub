package com.chat.chat.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class ChatController {

    private final ChatService service;

    @Autowired
    public ChatController(ChatService service) {
        this.service = service;
    }

    @GetMapping("status")
    public ResponseEntity getStatus200() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("users")
    public ChatUser createUser() {
        return service.createUser();
    }
}
