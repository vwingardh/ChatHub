package com.chat.chat.customExceptions;

public class InvalidUsernameLengthException extends RuntimeException {

    public InvalidUsernameLengthException(int usernameLength) {
        super(usernameLength <= 4 ? "Username must be at least 5 characters" : "Username must be less than 30 characters");
    }
}
