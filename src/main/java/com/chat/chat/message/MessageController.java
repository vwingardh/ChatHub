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
    private final ApiResponse response;

    @Autowired
    public MessageController(MessageService service, ApiResponse response) {
        this.service = service;
        this.response = response;
    }

    @GetMapping("message-status")
    public ResponseEntity<ApiResponse> getStatusCode200() {
        response.setStatus("API is operational");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("messages/{id}")
    public ResponseEntity<ApiResponse> getMessageById(@PathVariable String id) {
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

    @GetMapping("messages/user/{userId}/channel/{channelId}")
    public ResponseEntity<ApiResponse> getMessagesByUserIdChannelId(@PathVariable String userId, @PathVariable String channelId) {
        try {
            List<Message> messages = service.getMessagesByUserIdChannelId(Long.valueOf(userId), Long.valueOf(channelId));
            response.setData(messages);
            return ResponseEntity.ok().body(response);
        } catch (NoSuchElementException e) {
            response.setError(response.getError());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("messages/channel/{channelId}")
    public ResponseEntity<ApiResponse> getMessagesByChannelId(@PathVariable String channelId) {
        try {
            List<Message> messages = service.getMessagesByChannelId(Long.valueOf(channelId));
            response.setData(messages);
            return ResponseEntity.ok().body(response);
        } catch (NoSuchElementException e) {
            response.setError(response.getError());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("messages")
    public ResponseEntity<ApiResponse> createMessage(@RequestBody MessageDtoRequest messageDto) {
        try {
            Message newMessage = service.createMessage(messageDto.messageText(), messageDto.userId(), messageDto.channelId());
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
