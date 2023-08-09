"use client";
import { TextField } from "@mui/material";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
import { useEffect, useState } from "react";
import axios from "axios";

type ChatroomProps = {
    id: string;
    name: string;
};

export default function SearchChatrooms() {
    const [searchQuery, setSearchQuery] = useState("");
    const [retrievedChatrooms, setRetrievedChatrooms] = useState<Array<ChatroomProps>>([]);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/chatrooms")
            .then((response) => {
                setRetrievedChatrooms(response.data.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    const filteredChatrooms = retrievedChatrooms.filter((chatroom) => 
    chatroom.name.toLowerCase().startsWith(searchQuery.toLowerCase())
);

    return (
        <>
            <div style={{ marginTop: "200px", marginLeft: "200px" }}>
                <TextField
                    id="search-bar"
                    className="text"
                    onInput={(e: React.ChangeEvent<HTMLInputElement>) => {
                        setSearchQuery(e.target.value);
                    }}
                    label="Enter a chatroom name"
                    variant="outlined"
                    placeholder="Search..."
                    size="small"
                />
                <IconButton aria-label="search">
                    <SearchIcon style={{ fill: "blue" }} />
                </IconButton>

                {searchQuery && (
                    <div>
                    {filteredChatrooms.map(chatroom => (
                        <div key={chatroom.id}>{chatroom.name}</div>
                    ))}
                </div>
                )}
            </div>
        </>
    );
}
