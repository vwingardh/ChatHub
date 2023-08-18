"use client";
import { useEffect, useState } from "react";
import DisplayMessage from "../components/DisplayMessage";
import axios from "axios";
import MessageForm from "../components/MessageForm";
import { connect, disconnect } from "../lib/websocket";

type Message = {
    id: string;
    messageText: string;
    created: string;
    sender: {
        id: number;
        username: string;
    };
    channel: {
        id: number;
        link: string;
        name: string;
    };
};

type StompMessageFrame = {
    body: string;
};

export default function ChannelLobby() {
    const [messages, setMessages] = useState<Array<Message>>([]);
    const [messageError, setMessageError] = useState("");
    const [activeUsers, setActiveUsers] = useState(0);

    const channelId = sessionStorage.getItem("channelId");

    const joinChannel = async (channelId: string | null) => {
        if (!channelId) {
            console.error("Channel ID is missing from session storage.");
            return;
        }
        try {
            await axios.post(
                `http://localhost:8080/api/channels/${channelId}/join-channel`,
                {
                    headers: {
                        "Content-Type": "application/json",
                    },
                }
            );
        } catch (error) {
            console.error("Error joining channel: ", error);
        }
    };

    const leaveChannel = async (channelId: string | null) => {
        if (!channelId) {
            console.error("Channel ID is missing from session storage.");
            return;
        }
        try {
            await axios.patch(
                `http://localhost:8080/api/channels/${channelId}/decrement-users`,
                {
                    headers: {
                        "Content-Type": "application/json",
                    },
                }
            );
        } catch (error) {
            console.error(error);
        }
    };

    const handleActiveUsersUpdate = (message: StompMessageFrame) => {
        setActiveUsers(JSON.parse(message.body));
    };

    useEffect(() => {
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

        axios
            .get(`http://localhost:8080/api/channels/${channelId}/active-users`)
            .then((response) => {
                setActiveUsers(response.data.data);
            })
            .catch((error) => {
                console.error(error);
            });

        if (channelId) {
            connect(
                channelId,
                (messageFrame: StompMessageFrame) => {
                    try {
                        const newMessage = JSON.parse(messageFrame.body);
                        setMessages((prevMessages) => [
                            ...prevMessages,
                            newMessage,
                        ]);
                    } catch (error) {
                        console.error(
                            "Error parsing WebSocket payload: ",
                            error
                        );
                    }
                },
                handleActiveUsersUpdate
            );
            joinChannel(channelId);
        }

        return () => {
            disconnect();
            if (channelId) {
                leaveChannel(channelId);
            }
        };
    }, [channelId]);

    return (
        <main>
            <DisplayMessage messages={messages} activeUsers={activeUsers} />
            <MessageForm />
            {messageError && <div>{messageError}</div>}
        </main>
    );
}
