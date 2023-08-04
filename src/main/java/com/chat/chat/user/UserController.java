package com.chat.chat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("status")
    public ResponseEntity getStatus200() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("users")
    public ChatUser createUser(@RequestParam("username") String username) {
        return service.createUser(username);
    }
}
