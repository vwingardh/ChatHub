"use client";
import axios from "axios";
import { useEffect, useState } from "react";

type DisplayMessageProps = {
    messageId: string | null;
};

type MessageDisplay = {
    id: string;
    message: string;
    messageText: string;
}

export default function DisplayMessage({ messageId }: DisplayMessageProps) {
    const [messageText, setMessageText] = useState<Array<MessageDisplay>>([]);
    const [messageTextError, setMessageTextError] = useState("");
    const userId = sessionStorage.getItem("userId");
    const chatroomId = sessionStorage.getItem("chatroomId");

    console.log(chatroomId)

    useEffect(() => {
        if (messageId) {
            axios
                .get("http://localhost:8080/api/messages/chatroom/" + chatroomId)
                .then((response) => {
                    setMessageText(response.data.data);
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
    }, [chatroomId, messageId, userId]);

    return (
        <>
            {messageText && messageText.map((message: MessageDisplay) => (
                <div key={message.id}>{message.messageText}</div>
            ))}
        </>
    );
}
