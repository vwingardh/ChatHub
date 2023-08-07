"use client";
import { FormControl, Input, InputLabel, FormHelperText } from "@mui/material";
import { useState } from "react";
import ChatroomNameSubmitButton from "./ChatroomNameSubmitButton";

export default function ChatroomForm() {
    const [chatroomName, setChatroomName] = useState("");
    const [chatroomNameError, setChatroomNameError] = useState("");

    const handleChatroomName = (event: React.ChangeEvent<HTMLInputElement>) => {
        setChatroomName(event.target.value);
        setChatroomNameError("");
    };

    return (
        <>
            <FormControl>
                <InputLabel htmlFor="chatroomName" required>
                    Chatroom
                </InputLabel>
                <Input
                    onChange={handleChatroomName}
                    id="chatroomName"
                    aria-describedby="chatroomName"
                />
                <FormHelperText id="chatroomName">
                    Please create a chatroom.
                </FormHelperText>
                <ChatroomNameSubmitButton
                    chatroomName={chatroomName}
                    setChatroomNameError={setChatroomNameError}
                />
                {chatroomNameError && <div>{chatroomNameError}</div>}
            </FormControl>
        </>
    );
}
