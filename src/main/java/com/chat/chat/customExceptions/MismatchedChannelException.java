package com.chat.chat.customExceptions;

public class MismatchedChannelException extends RuntimeException {

    public MismatchedChannelException(String message) {
        super(message);
    }
}
