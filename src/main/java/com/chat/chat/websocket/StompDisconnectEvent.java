package com.chat.chat.websocket;

import com.chat.chat.channel.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {

    private final ChannelService channelService;
    private final SessionChannelMapping sessionChannelMapping;

    @Autowired
    public StompDisconnectEvent(ChannelService channelService, SessionChannelMapping sessionChannelMapping) {
        this.channelService = channelService;
        this.sessionChannelMapping = sessionChannelMapping;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();

        Long channelId = sessionChannelMapping.getChannelId(sessionId);
        if (channelId != null) {
            channelService.decrementActiveUsers(channelId);
            sessionChannelMapping.removeMapping(sessionId);
        }
    }
}


