"use client";
import {
    Button,
    Container,
    FormControl,
    Input,
    InputLabel,
} from "@mui/material";
import { useState } from "react";
import JoinChannelSubmit from "./JoinChannelSubmit";

export default function JoinChannelLinkForm() {
    const [channelLink, setChannelLink] = useState("");
    const [submitLink, setSubmitLink] = useState("");

    const handleSubmit = (e: React.MouseEvent) => {
        e.preventDefault();
        setSubmitLink(channelLink);
    };

    return (
        <Container className="container--channel-link-form">
            <FormControl className="form">
                <InputLabel htmlFor="channelLink" required>
                    Channel Link
                </InputLabel>
                <Input
                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        setChannelLink(e.target.value);
                    }}
                    id="channelLink"
                    placeholder="Paste channel link..."
                    aria-describedby="channel link"
                    required
                />
                <Button className="button--submit" onClick={handleSubmit}>
                    Join
                </Button>
                {submitLink && (
                    <JoinChannelSubmit channelLink={submitLink} />
                )}
            </FormControl>
        </Container>
    );
}
