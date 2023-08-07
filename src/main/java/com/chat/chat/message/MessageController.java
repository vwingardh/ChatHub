package com.chat.chat.message;

import com.chat.chat.customApiResponses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MessageController {

    @GetMapping("message-status")
    public ResponseEntity<ApiResponse> getStatusCode200() {
        ApiResponse response = new ApiResponse();
        response.setStatus("API is operational");
        return ResponseEntity.ok().body(response);
    }
}
