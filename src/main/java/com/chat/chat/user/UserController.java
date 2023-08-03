package com.chat.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin("*")
public class Controller {

    @GetMapping("status")
    public ResponseEntity getStatus200() {
        return ResponseEntity.ok().build();
    }

}
