package com.chat.chat.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MessageDto(
        @NotBlank @Size(max = 500) String messageText,
        @NotNull Long userId,
        @NotNull Long channelId) {
}
