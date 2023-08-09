"use client";
import { Button } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

type ChatroomNameProps = {
    chatroomName: string;
    setChatroomNameError: React.Dispatch<React.SetStateAction<string>>;
};

export default function ChatroomNameSubmitButton({
    chatroomName,
    setChatroomNameError,
}: ChatroomNameProps) {

    const router = useRouter();
    const data = sessionStorage;

    const handleChatroomNameSubmit = () => {
        axios
            .post("http://localhost:8080/api/chatrooms", {
                chatroomName: chatroomName,
            })
            .then((response) => {
                const link = response.data.data.link;
                data.setItem("chatroomId", response.data.data.id)
                alert("This is the link for your friends to join: " + link)
                router.push("/chatroom-lobby")
            })
            .catch((error) => {
                if (
                    error.response &&
                    error.response.data &&
                    error.response.data.error
                ) {
                    setChatroomNameError(error.response.data.error);
                } else {
                    setChatroomNameError("An unexpected error occurred.");
                }
                console.error(error);
            });
    };

    return (
        <>
            <Button onClick={handleChatroomNameSubmit}>Create</Button>
        </>
    );
}
