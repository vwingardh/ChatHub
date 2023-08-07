"use client";
import axios from "axios";
import { useEffect, useState } from "react";

type DisplayMessageProps = {
    messageId: string | null;
};

export default function DisplayMessage({ messageId }: DisplayMessageProps) {
    const [messageText, setMessageText] = useState("");
    const [messageTextError, setMessageTextError] = useState("");

    useEffect(() => {
        if (messageId) {
            axios
                .get("http://localhost:8080/api/messages/" + messageId)
                .then((response) => {
                    setMessageText(response.data.data.messageText);
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
        }
    }, [messageId]);

    return (
        <>
            {messageTextError ? <div>{messageTextError}</div> : <div>{messageText}</div>}
        </>
    );
}
