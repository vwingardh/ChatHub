"use client";
import { Button, FormControl, Input, InputLabel } from "@mui/material";
import { useState } from "react";
import JoinChatroomSubmitButton from "./JoinChatroomSubmit";

export default function JoinChatroomLinkForm() {
    const [chatroomLink, setChatroomLink] = useState("");
    const [submitLink, setSubmitLink] = useState("");

    const handleSubmit = (e: React.MouseEvent) => {
        e.preventDefault();
        setSubmitLink(chatroomLink);
    };

    return (
        <FormControl style={{ marginTop: "200px", marginLeft: "200px" }}>
            <InputLabel htmlFor="chatroomLink" required>
                Enter Chatroom Link
            </InputLabel>
            <Input
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                    setChatroomLink(e.target.value);
                }}
                id="chatroomLink"
                aria-describedby="chatroom link"
                required
            />
            <Button onClick={handleSubmit}>Join</Button>
            {submitLink && (
                <JoinChatroomSubmitButton chatroomLink={submitLink} />
            )}
        </FormControl>
    );
}
