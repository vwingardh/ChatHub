package com.chat.chat.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class UserController {

    @GetMapping("status")
    public ResponseEntity getStatus200() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("users")
    public ChatUser createUser() {
        ChatUser user = new ChatUser(1, "vileshocka");
        return user;
    }
}
