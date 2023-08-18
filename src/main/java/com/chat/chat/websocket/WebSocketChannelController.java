package com.chat.chat.websocket;

import com.chat.chat.channel.ChannelService;
import com.chat.chat.customExceptions.MismatchedChannelException;
import com.chat.chat.message.Message;
import com.chat.chat.message.MessageDtoRequest;
import com.chat.chat.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketChannelController {

    private final MessageService messageService;
    private final ChannelService channelService;

    @Autowired
    public WebSocketChannelController(MessageService messageService, ChannelService channelService) {
        this.messageService = messageService;
        this.channelService = channelService;
    }

    @MessageMapping("/channel/{channelId}")
    @SendTo("/channel/{channelId}")
    public Message broadcastMessage(@DestinationVariable Long channelId, MessageDtoRequest messageDto) {
        if (!channelId.equals(messageDto.channelId())) {
            throw new MismatchedChannelException("Provided channel ID does not match the message's channel ID");
        }
        Message message = messageService.createMessage(messageDto.messageText(), messageDto.userId(), messageDto.channelId());
        return message;
    }

    @MessageMapping("/active-users/{channelId}")
    @SendTo("/active-users/{channelId}")
    public ActiveUsersDtoResponse getActiveUsers(@DestinationVariable Long channelId) {
        Long activeUsersCount = channelService.getActiveUsers(channelId);
        return new ActiveUsersDtoResponse(activeUsersCount);
    }

}
