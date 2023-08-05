package com.chat.chat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class ChatUserController {

    private final ChatUserService service;

    @Autowired
    public ChatUserController(ChatUserService service) {
        this.service = service;
    }

    @GetMapping("status")
    public ResponseEntity getStatus200() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<ChatUser> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("users")
    public ChatUser createUser(@RequestParam("username") String username) {
        return service.createUser(username);
    }
}
