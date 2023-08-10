"use client";
import { FormControl, Input, InputLabel, FormHelperText } from "@mui/material";
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
        <>
            <FormControl>
                <InputLabel htmlFor="channelName" required>
                Channel
                </InputLabel>
                <Input
                    onChange={handleChannelName}
                    id="channelName"
                    aria-describedby="channelName"
                />
                <FormHelperText id="channelName">
                    Please create a channel.
                </FormHelperText>
                <ChannelNameSubmitButton
                    channelName={channelName}
                    setChannelNameError={setChannelNameError}
                />
                {channelNameError && <div>{channelNameError}</div>}
            </FormControl>
        </>
    );
}
