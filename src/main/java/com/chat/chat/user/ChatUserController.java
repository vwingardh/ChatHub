package com.chat.chat.user;

import com.chat.chat.customApiResponses.ApiResponse;
import com.chat.chat.customExceptions.InvalidUsernameLengthException;
import com.chat.chat.customExceptions.UsernameAlreadyTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ChatUserController {

    private final ChatUserService service;

    @Autowired
    public ChatUserController(ChatUserService service) {
        this.service = service;
    }

    @GetMapping("user-status")
    public ResponseEntity<ApiResponse> getStatus200() {
        ApiResponse response = new ApiResponse();
        response.setStatus("API is operational");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public List<ChatUser> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("users")
    public ResponseEntity<ApiResponse> createUser(@RequestBody ChatUserDto userDto) {
        ApiResponse response = new ApiResponse();
        try {
            ChatUser newUser = service.createUser(userDto.username());
            response.setData(newUser);
            return ResponseEntity.created(URI.create("/api/users/" + newUser.getId().toString()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (InvalidUsernameLengthException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (UsernameAlreadyTakenException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
