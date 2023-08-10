package com.chat.chat.websocket;

import com.chat.chat.customExceptions.MismatchedChannelException;
import com.chat.chat.message.Message;
import com.chat.chat.message.MessageDtoRequest;
import com.chat.chat.message.MessageDtoResponse;
import com.chat.chat.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketChannelController {

    private final MessageService messageService;

    @Autowired
    public WebSocketChannelController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/channel/{channelId}")
    @SendTo("/channel/{channelId}")
    public MessageDtoResponse broadcastMessage(@DestinationVariable Long channelId, MessageDtoRequest messageDto) {
        if (!channelId.equals(messageDto.channelId())) {
            throw new MismatchedChannelException("Provided channel ID does not match the message's channel ID");
        }
        Message message = messageService.createMessage(messageDto.messageText(), messageDto.userId(), messageDto.channelId());
        MessageDtoResponse messageResponse = new MessageDtoResponse(message.getId(), message.getMessageText(), message.getCreated());
        return messageResponse;
    }
}