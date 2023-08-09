"use client";
import { Button } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState, useEffect } from "react";

type JoinChatroomLinkProp = {
    chatroomLink: string;
};

export default function JoinChatroomSubmitButton({
    chatroomLink,
}: JoinChatroomLinkProp) {

    const router = useRouter();
    const data = sessionStorage;

    useEffect(() => {
        if (chatroomLink)
        axios.get("http://localhost:8080/api/chatrooms/" + chatroomLink)
        .then((response) => {
            data.setItem("chatroomId", response.data.data.id);
            router.push("/chatroom-lobby")
        })
        .catch((error) => {
            console.error(error);
        });
    }, [chatroomLink, data, router]);
    
    return null;
}
