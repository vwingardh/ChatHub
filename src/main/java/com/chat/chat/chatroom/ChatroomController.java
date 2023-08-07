package com.chat.chat.chatroom;

import com.chat.chat.customApiResponses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ChatroomController {

    private final ChatroomService service;

    @Autowired
    public ChatroomController(ChatroomService service) {
        this.service = service;
    }

    @GetMapping("chatroom-status")
    public ResponseEntity<ApiResponse> getStatusCode200() {
        ApiResponse response = new ApiResponse();
        response.setStatus("Api is operational");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("chatroom")
    public Chatroom createChatroom(@RequestBody String chatroomName) {
        return service.createChatroom(chatroomName);
    }

}
