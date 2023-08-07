"use client";
import { Button } from "@mui/material";
import axios from "axios";

type ChatroomNameProps = {
    chatroomName: string;
    setChatroomNameError: React.Dispatch<React.SetStateAction<string>>;
};

export default function ChatroomNameSubmitButton({
    chatroomName,
    setChatroomNameError,
}: ChatroomNameProps) {
    const handleChatroomNameSubmit = () => {
        axios
            .post("http://localhost:8080/api/chatrooms", {
                chatroomName: chatroomName,
            })
            .then((response) => {
                console.log(response);
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
