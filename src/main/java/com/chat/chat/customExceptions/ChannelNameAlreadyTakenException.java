package com.chat.chat.customExceptions;

public class ChannelNameAlreadyTakenException extends RuntimeException {

    public ChannelNameAlreadyTakenException(String channelName) {
        super("Channel name '" + channelName + "' is already taken.");
    }
}
