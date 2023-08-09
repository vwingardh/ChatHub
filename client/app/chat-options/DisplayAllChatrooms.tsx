"use client";
import { List, ListItem, ListItemText, ListSubheader } from "@mui/material";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

type ChatroomProps = {
    id: string;
    name: string;
};

export default function DisplayAllChatrooms() {
    const [chatrooms, setChatrooms] = useState<Array<ChatroomProps>>([]);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/chatrooms")
            .then((response) => {
                setChatrooms(response.data.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    return (
        <List
            sx={{
                width: "100%",
                maxWidth: 360,
                bgcolor: "background.paper",
                position: "relative",
                overflow: "auto",
                maxHeight: 300,
                "& ul": { padding: 0 },
            }}
            subheader={<li />}
        >
            {chatrooms.map((chatroom) => (
                <ListItem key={chatroom.id}>
                    <Link href={`/messaging/${chatroom.id}`}>
                        {chatroom.name}
                    </Link>
                </ListItem>
            ))}
        </List>
    );
}
