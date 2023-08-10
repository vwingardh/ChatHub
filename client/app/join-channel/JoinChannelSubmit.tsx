"use client";
import { Button } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState, useEffect } from "react";

type JoinChannelLinkProp = {
    channelLink: string;
};

export default function JoinChannelSubmitButton({
    channelLink,
}: JoinChannelLinkProp) {

    const router = useRouter();
    const data = sessionStorage;

    useEffect(() => {
        if (channelLink)
        axios.get("http://localhost:8080/api/channels/" + channelLink)
        .then((response) => {
            data.setItem("channelId", response.data.data.id);
            router.push("/channel-lobby")
        })
        .catch((error) => {
            console.error(error);
        });
    }, [channelLink, data, router]);
    
    return null;
}