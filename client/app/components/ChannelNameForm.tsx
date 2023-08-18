"use client";
import {
    FormControl,
    Input,
    InputLabel,
    FormHelperText,
    Container,
} from "@mui/material";
import { useState } from "react";
import ChannelNameSubmitButton from "./ChannelNameSubmitButton";

export default function ChannelNameForm() {
    const [channelName, setChannelName] = useState("");
    const [channelNameError, setChannelNameError] = useState("");

    const handleChannelName = (event: React.ChangeEvent<HTMLInputElement>) => {
        setChannelName(event.target.value);
        setChannelNameError("");
    };

    return (
        <Container className="container--channel-form">
            <FormControl className="form" variant="outlined">
                <InputLabel
                    htmlFor="channelName"
                    error={!!channelNameError}
                    required
                >
                    Channel
                </InputLabel>
                <Input
                    onChange={handleChannelName}
                    id="channelName"
                    placeholder="e.g. Tech Talk"
                    aria-describedby="channelName-helper"
                    error={!!channelNameError}
                />
                <FormHelperText
                    id="channelName-helper"
                    error={!!channelNameError}
                >
                    {channelNameError
                        ? channelNameError
                        : "Please create a channel"}
                </FormHelperText>

                <ChannelNameSubmitButton
                    channelName={channelName}
                    setChannelNameError={setChannelNameError}
                />
            </FormControl>
        </Container>
    );
}
