package com.chat.chat.message;

import com.chat.chat.customApiResponses.ApiResponse;
import com.chat.chat.customExceptions.MessageExceedsMaximumException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/")
public class MessageController {

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("message-status")
    public ResponseEntity<ApiResponse> getStatusCode200() {
        ApiResponse response = new ApiResponse();
        response.setStatus("API is operational");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("messages/{id}")
    public ResponseEntity<ApiResponse> getMessageById(@PathVariable String id) {
        ApiResponse response = new ApiResponse();
        try {
            Long convertedId = Long.valueOf(id);
            Message message = service.getMessageById(convertedId);
            response.setData(message);
            return ResponseEntity.ok().body(response);
        } catch (NoSuchElementException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("messages/user/{userId}/chatroom/{chatroomId}")
    public ResponseEntity<ApiResponse> getMessagesByUserIdChatroomId(@PathVariable String userId, @PathVariable String chatroomId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Message> messages = service.getMessagesByUserIdChatroomId(Long.valueOf(userId), Long.valueOf(chatroomId));
            response.setData(messages);
            return ResponseEntity.ok().body(response);
        } catch (NoSuchElementException e) {
            response.setError(response.getError());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PostMapping("messages")
    public ResponseEntity<ApiResponse> createMessage(@RequestBody MessageDto messageDto) {
        ApiResponse response = new ApiResponse();
        try {
            Message newMessage = service.createMessage(messageDto.messageText(), messageDto.userId(), messageDto.chatroomId());
            response.setData(newMessage);
            return ResponseEntity.created(URI.create("/api/messages/" + newMessage.getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (MessageExceedsMaximumException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (NoSuchElementException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
