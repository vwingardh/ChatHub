"use client";
import { Button } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

type ChannelNameProps = {
    channelName: string;
    setChannelNameError: React.Dispatch<React.SetStateAction<string>>;
};

export default function ChannelNameSubmitButton({
    channelName,
    setChannelNameError,
}: ChannelNameProps) {

    const router = useRouter();
    const data = sessionStorage;

    const handleChannelNameSubmit = () => {
        axios
            .post("http://localhost:8080/api/channels", {
                channelName: channelName,
            })
            .then((response) => {
                const link = response.data.data.link;
                data.setItem("channelId", response.data.data.id)
                alert("This is the link for your friends to join: " + link)
                router.push("/channel-lobby")
            })
            .catch((error) => {
                if (
                    error.response &&
                    error.response.data &&
                    error.response.data.error
                ) {
                    setChannelNameError(error.response.data.error);
                } else {
                    setChannelNameError("An unexpected error occurred.");
                }
                console.error(error);
            });
    };

    return (
        <>
            <Button onClick={handleChannelNameSubmit}>Create</Button>
        </>
    );
}
