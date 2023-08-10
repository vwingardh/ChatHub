"use client";
import { Button, FormControl, Input, InputLabel } from "@mui/material";
import { useState } from "react";
import JoinChannelSubmitButton from "./JoinChannelSubmit";

export default function JoinChannelLinkForm() {
    const [channelLink, setChannelLink] = useState("");
    const [submitLink, setSubmitLink] = useState("");

    const handleSubmit = (e: React.MouseEvent) => {
        e.preventDefault();
        setSubmitLink(channelLink);
    };

    return (
        <FormControl style={{ marginTop: "200px", marginLeft: "200px" }}>
            <InputLabel htmlFor="channelLink" required>
                Enter Channel Link
            </InputLabel>
            <Input
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                    setChannelLink(e.target.value);
                }}
                id="channelLink"
                aria-describedby="channel link"
                required
            />
            <Button onClick={handleSubmit}>Join</Button>
            {submitLink && (
                <JoinChannelSubmitButton channelLink={submitLink} />
            )}
        </FormControl>
    );
}
