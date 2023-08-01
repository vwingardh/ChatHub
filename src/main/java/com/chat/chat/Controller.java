package com.chat.chat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class Controller {

    @GetMapping("status")
    public String getStatus() {
        return "hello";
    }
}
