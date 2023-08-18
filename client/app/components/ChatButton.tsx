"use client";
import { Box, Button as MuiButton, Container } from "@mui/material";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function ChatButton() {
    const [userId, setUserId] = useState<string | null>(null);

    useEffect(() => {
        const storedUserId = sessionStorage.getItem("userId");
        setUserId(storedUserId);
    }, []);

    const targetLink = userId ? "/chat-options" : "/user";

    return (
        <Container className="container--standard">
            <Box>
                <Link href={targetLink} passHref>
                    <MuiButton className="button--route" variant="contained">
                        Chat Now
                    </MuiButton>
                </Link>
            </Box>
        </Container>
    );
}
