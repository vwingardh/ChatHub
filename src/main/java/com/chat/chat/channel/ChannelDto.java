package com.chat.chat.channel;

import jakarta.validation.constraints.NotBlank;

public record ChannelDto(@NotBlank String channelName) {
}
