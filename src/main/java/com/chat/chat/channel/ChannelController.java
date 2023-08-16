package com.chat.chat.channel;

import com.chat.chat.customApiResponses.ApiResponse;
import com.chat.chat.customExceptions.ChannelNameAlreadyTakenException;
import com.chat.chat.customExceptions.InvalidChannelNameLengthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ChannelController {

    private final ChannelService service;

    @Autowired
    public ChannelController(ChannelService service) {
        this.service = service;
    }

    @GetMapping("channel-status")
    public ResponseEntity<ApiResponse> getStatusCode200() {
        ApiResponse response = new ApiResponse();
        response.setStatus("API is operational");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("channels")
    public ResponseEntity<ApiResponse> getAllChannels() {
        ApiResponse response = new ApiResponse();
        List<Channel> channelList = service.getAllChannels();
        response.setData(channelList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("channels/{joinLink}")
    public ResponseEntity<ApiResponse> getChannelByChannelLink(@PathVariable String joinLink) {
        ApiResponse response = new ApiResponse();
        Channel channel = service.getChannelByChannelLink(joinLink);
        response.setData(channel);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("channels/{channelId}/active-users")
    public ResponseEntity<ApiResponse> getActiveUserCount(@PathVariable String channelId) {
        ApiResponse response = new ApiResponse();
        Long activeUsers = service.getActiveUsers(Long.valueOf(channelId));
        response.setData(activeUsers);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("channels/{channelId}/join-channel")
    public ResponseEntity<ApiResponse> joinChannel(@PathVariable String channelId) {
        ApiResponse response = new ApiResponse();
        response.setStatus("User added to channel");
        service.incrementActiveUsers(Long.valueOf(channelId));
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("channels/{channelId}/decrement-users")
    public ResponseEntity<ApiResponse> decrementActiveUsers(@PathVariable String channelId) {
        ApiResponse response = new ApiResponse();
        response.setStatus("User removed from channel");
        service.decrementActiveUsers(Long.valueOf(channelId));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("channels")
    public ResponseEntity<ApiResponse> createChannel(@RequestBody ChannelDto channelDto) {
        ApiResponse response = new ApiResponse();
        try {
            Channel channel = service.createChannel(channelDto.channelName());
            response.setData(channel);
            return ResponseEntity.created(URI.create("/api/channels/" + channel.getId().toString()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch(ChannelNameAlreadyTakenException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (InvalidChannelNameLengthException e) {
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
