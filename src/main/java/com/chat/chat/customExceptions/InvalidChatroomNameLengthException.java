package com.chat.chat.customExceptions;

public class InvalidChatroomNameLengthException extends RuntimeException {

    public InvalidChatroomNameLengthException(int chatroomNameLength) {
        super(chatroomNameLength <= 4 ? "Chatroom name must be at least 5 characters" : "Chatroom name must be 30 characters or less");
    }
}
