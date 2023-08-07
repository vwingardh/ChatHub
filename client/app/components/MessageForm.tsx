"use client";
import { FormControl, InputLabel, Input, Button } from "@mui/material";
import { useState } from "react";
import axios from "axios";

type MessageFormProps = {
    setMessageTextError: React.Dispatch<React.SetStateAction<string>>;
    setMessageId: React.Dispatch<React.SetStateAction<string>>;
};

export default function MessageForm({
    setMessageTextError,
    setMessageId,
}: MessageFormProps) {
    const [messageText, setMessageText] = useState("");

    const handleMessageText = (event: React.ChangeEvent<HTMLInputElement>) => {
        setMessageText(event.target.value);
        setMessageTextError("");
    };

    const handleMessageSubmit = () => {
        axios
            .post("http://localhost:8080/api/messages", {
                messageText: messageText,
            })
            .then((response) => {
                setMessageId(response.data.data.id);
            })
            .catch((error) => {
                if (
                    error.response &&
                    error.response.data &&
                    error.response.data.error
                ) {
                    setMessageTextError(error.response.data.error);
                } else {
                    setMessageTextError("An unexpected error occurred.");
                }
            });
    };

    return (
        <>
            <FormControl style={{ marginTop: "200px", marginLeft: "200px" }}>
                <InputLabel htmlFor="username" required>
                    Type message
                </InputLabel>
                <Input
                    onChange={handleMessageText}
                    id="username"
                    aria-describedby="username"
                    inputProps={{ maxLength: 500 }}
                    required
                />
                <Button onClick={handleMessageSubmit}>Send</Button>
            </FormControl>
        </>
    );
}
