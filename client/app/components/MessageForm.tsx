"use client";
import {
    FormControl,
    InputLabel,
    Input,
    Button,
    Container,
} from "@mui/material";
import { useState } from "react";
import axios from "axios";
import { sendMessage } from "../lib/websocket";

export default function MessageForm() {
    const [messageText, setMessageText] = useState("");
    const userId = sessionStorage.getItem("userId");
    const channelId = sessionStorage.getItem("channelId");
    const channelName = sessionStorage.getItem("channelName");

    const handleMessageText = (event: React.ChangeEvent<HTMLInputElement>) => {
        setMessageText(event.target.value);
    };

    const handleMessageSubmit = () => {
        if (channelId) {
            axios
                .post("http://localhost:8080/api/messages", {
                    messageText: messageText,
                    userId: userId,
                    channelId: channelId,
                })
                .then((response) => {
                    sendMessage(messageText, userId, channelId);
                    setMessageText("");
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };

    return (
        <Container className="container--message-form">
            <FormControl className="form">
                <InputLabel htmlFor="message">Message {channelName}</InputLabel>
                <Input
                    value={messageText}
                    onChange={handleMessageText}
                    id="message"
                    aria-describedby="message"
                    inputProps={{ maxLength: 500 }}
                    required
                />
                <Button
                    className="button--submit"
                    onClick={handleMessageSubmit}
                >
                    Post
                </Button>
            </FormControl>
        </Container>
    );
}
