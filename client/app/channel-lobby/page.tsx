"use client";
import { useEffect, useState } from "react";
import DisplayMessage from "../components/DisplayMessage";
import axios from "axios";
import MessageForm from "../components/MessageForm";
import { connect, disconnect } from "../lib/websocket"


type MessageDtoResponse = {
    id: string;
    messageText: string;
    created: string;
}

type StompMessageFrame = {
    body: string;
};

export default function ChannelLobby() {

    const [messages, setMessages] = useState<Array<MessageDtoResponse>>([]);
    const [messageError, setMessageError] = useState("")

    useEffect(() => {
        const channelId = sessionStorage.getItem("channelId");

        axios
        .get("http://localhost:8080/api/messages/channel/" + channelId)
        .then((response) => {
            setMessages(response.data.data);
        })
        .catch((error) => {
            if (
                error.response &&
                error.response.data &&
                error.response.data.error
            ) {
                setMessageError(error.response.data.error);
            } else {
                setMessageError("An unexpected error occurred.");
            }
        });

        if (channelId) {
            connect(channelId, (messageFrame: StompMessageFrame) => {
                try {
                    const newMessage = JSON.parse(messageFrame.body);
                    console.log(newMessage)
                    setMessages(prevMessages => [...prevMessages, newMessage]);
                } catch (error) {
                    console.error("Error parsing WebSocket payload: ", error);
                }
            })
        }

        return () => {
            disconnect();
        };
    }, []);

    const handleSendMessage = (newMessage: MessageDtoResponse) => {
        setMessages(prevMessages => [...prevMessages, newMessage]);
    }

    return (
        <main>
            <DisplayMessage messages={messages} />
            <MessageForm onSend={handleSendMessage} />
            {messageError && <div>{messageError}</div>}
        </main>
    )
}