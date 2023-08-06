"use client";
import { Button } from "@mui/material";
import axios from "axios";
import router, { useRouter } from "next/router";

type UsernameProps = {
    username: string;
    setUsernameError: React.Dispatch<React.SetStateAction<string>>;
};

export default function UsernameSubmitButton({
    username,
    setUsernameError,
}: UsernameProps) {
    
    const handleUsernameSubmit = () => {
        axios
            .post("http://localhost:8080/api/users", {
                username: username,
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
