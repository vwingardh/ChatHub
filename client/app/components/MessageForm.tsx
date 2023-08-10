"use client";
import { FormControl, InputLabel, Input, Button } from "@mui/material";
import { useState } from "react";
import axios from "axios";
import { sendMessage } from "../lib/websocket";


type MessageFormProps = {
    onSend: (message: any) => void;
};


export default function MessageForm({ onSend }: MessageFormProps) {
    const [messageText, setMessageText] = useState("");
    const userId = sessionStorage.getItem("userId");
    const channelId = sessionStorage.getItem("channelId");

    const handleMessageText = (event: React.ChangeEvent<HTMLInputElement>) => {
        setMessageText(event.target.value);
    };

    const handleMessageSubmit = () => {
        axios.post("http://localhost:8080/api/messages", {
            messageText: messageText,
            userId: userId,
            channelId: channelId
        })
        .then((response) => {
            sendMessage(messageText, userId, channelId)
            setMessageText("");
        })
        .catch((error) => {
            console.error(error);
        })
    };

    return (
        <>
            <FormControl style={{ marginTop: "200px", marginLeft: "200px" }}>
                <InputLabel htmlFor="username" required>
                    Type message
                </InputLabel>
                <Input
                    value={messageText}
                    onChange={handleMessageText}
                    id="username"
                    aria-describedby="username"
                    inputProps={{ maxLength: 500 }}
                    required
                />
                <Button onClick={handleMessageSubmit}>Post</Button>
            </FormControl>
        </>
    );
}
