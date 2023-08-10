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
