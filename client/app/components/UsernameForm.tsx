"use client";
import { FormControl, InputLabel, Input, FormHelperText, Container } from "@mui/material";
import { useState } from "react";
import UsernameSubmitButton from "./UsernameSubmitButton";

export default function UsernameForm() {
    const [username, setUsername] = useState("");
    const [usernameError, setUsernameError] = useState("");

    const handleUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(event.target.value);
        setUsernameError("");
    };

    return (
        <>
        <Container className="container--channel-form">
            <FormControl className="form">
                <InputLabel htmlFor="username" required>
                    Username
                </InputLabel>
                <Input
                    onChange={handleUsername}
                    id="username"
                    aria-describedby="username"
                />
                <FormHelperText id="username">
                    Please create a username
                </FormHelperText>
                <UsernameSubmitButton
                    username={username}
                    setUsernameError={setUsernameError}
                />
                {usernameError && <div>{usernameError}</div>}
            </FormControl>
            </Container>
        </>
    );
}
