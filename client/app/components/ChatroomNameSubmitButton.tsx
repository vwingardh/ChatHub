"use client";
import { Button } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";

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
                console.log(response.data.data.id)
                sessionStorage.setItem("chatroomId", response.data.data.id)
                router.push("/messaging")
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
