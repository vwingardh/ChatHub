package com.chat.chat.chatroom;

import com.chat.chat.customApiResponses.ApiResponse;
import com.chat.chat.customExceptions.ChatroomNameAlreadyTakenException;
import com.chat.chat.customExceptions.InvalidChatroomNameLengthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @PostMapping("chatrooms")
    public ResponseEntity<ApiResponse> createChatroom(@RequestBody ChatroomDto chatroomDto) {
        ApiResponse response = new ApiResponse();
        try {
            Chatroom chatroom = service.createChatroom(chatroomDto.chatroomName());
            response.setData(chatroom);
            return ResponseEntity.created(URI.create("/api/chatrooms/" + chatroom.getId().toString()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch(ChatroomNameAlreadyTakenException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (InvalidChatroomNameLengthException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
