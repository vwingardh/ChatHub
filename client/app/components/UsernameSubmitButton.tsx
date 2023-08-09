"use client";
import { Button } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

type UsernameProps = {
    username: string;
    setUsernameError: React.Dispatch<React.SetStateAction<string>>;
};

export default function UsernameSubmitButton({
    username,
    setUsernameError,
}: UsernameProps) {
    
    const router = useRouter();
    const data = sessionStorage;

    const handleUsernameSubmit = () => {
        axios
            .post("http://localhost:8080/api/users", {
                username: username,
            })
            .then((response) => {
                data.setItem("userId", response.data.data.id);
                router.push("/chat-options")
            })
            .catch((error) => {
                if (
                    error.response &&
                    error.response.data &&
                    error.response.data.error
                ) {
                    setUsernameError(error.response.data.error);
                } else {
                    setUsernameError("An unexpected error occurred.");
                }
                console.error(error);
            });
    };

    return (
        <>
            <Button onClick={handleUsernameSubmit}>Create</Button>
        </>
    );
}
