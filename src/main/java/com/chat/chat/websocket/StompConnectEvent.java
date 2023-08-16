package com.chat.chat.websocket;

import com.chat.chat.channel.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;


@Component
public class StompConnectEvent implements ApplicationListener<SessionConnectEvent> {

    private final ChannelService channelService;
    private final SessionChannelMapping sessionChannelMapping;

    @Autowired
    public StompConnectEvent(ChannelService channelService, SessionChannelMapping sessionChannelMapping) {
        this.channelService = channelService;
        this.sessionChannelMapping = sessionChannelMapping;
    }

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        channelService.printAllKeys();

        String channelIdHeader = headerAccessor.getFirstNativeHeader("channelId");
        if (channelIdHeader!= null) {
            Long channelId = Long.valueOf(channelIdHeader);
            channelService.incrementActiveUsers(channelId);
            sessionChannelMapping.addMapping(sessionId, channelId);
        }
    }
}
