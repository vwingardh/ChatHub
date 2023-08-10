package com.chat.chat.customExceptions;

public class InvalidChannelNameLengthException extends RuntimeException {

    public InvalidChannelNameLengthException(int channelNameLength) {
        super(channelNameLength <= 4 ? "Channel name must be at least 5 characters" : "Channel name must be 30 characters or less");
    }
}
