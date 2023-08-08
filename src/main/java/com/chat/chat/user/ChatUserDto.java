package com.chat.chat.user;

import jakarta.validation.constraints.NotBlank;

public record ChatUserDto(@NotBlank String username) {
}
