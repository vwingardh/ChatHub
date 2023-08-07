package com.chat.chat.customExceptions;

public class ChatroomNameAlreadyTakenException extends RuntimeException {

    public ChatroomNameAlreadyTakenException(String chatroomName) {
        super("Chatroom name '" + chatroomName + "' is already taken.");
    }
}
