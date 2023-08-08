package com.chat.chat.chatroom;

import jakarta.validation.constraints.NotBlank;

public record ChatroomDto(@NotBlank String chatroomName) {
}
