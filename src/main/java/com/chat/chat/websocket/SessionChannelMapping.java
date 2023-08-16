package com.chat.chat.websocket;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionChannelMapping {

    private final Map<String, Long> sessionToChannel = new ConcurrentHashMap<>();

    public void addMapping(String sessionId, Long channelId) {
        sessionToChannel.put(sessionId, channelId);
    }

    public Long getChannelId(String sessionId) {
        return sessionToChannel.get(sessionId);
    }

    public void removeMapping(String sessionId) {
        sessionToChannel.remove(sessionId);
    }
}
