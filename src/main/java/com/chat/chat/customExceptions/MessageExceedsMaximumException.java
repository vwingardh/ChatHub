package com.chat.chat.customExceptions;

public class MessageExceedsMaximumException extends RuntimeException {

    public MessageExceedsMaximumException() {
        super("Text exceeds 500 characters");
    }
}
