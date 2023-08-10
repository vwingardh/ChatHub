package com.chat.chat.message;

import java.time.ZonedDateTime;

public record MessageDtoResponse(Long id, String messageText, ZonedDateTime created) {
}
