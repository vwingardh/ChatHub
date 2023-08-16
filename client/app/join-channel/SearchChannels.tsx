"use client";
import { TextField } from "@mui/material";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
import { useEffect, useState } from "react";
import axios from "axios";
import Link from "next/link";

type ChannelProps = {
    id: string;
    name: string;
};

export default function SearchChannels() {
    const [searchQuery, setSearchQuery] = useState("");
    const [retrievedChannel, setRetrievedChannels] = useState<Array<ChannelProps>>([]);

    const data = sessionStorage;

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/channels")
            .then((response) => {
                setRetrievedChannels(response.data.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    const filteredChannel = retrievedChannel.filter((channel) => 
    channel.name.toLowerCase().includes(searchQuery.toLowerCase())
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
                    label="Enter a channel name"
                    variant="outlined"
                    placeholder="Search..."
                    size="small"
                />
                <IconButton aria-label="search">
                    <SearchIcon style={{ fill: "blue" }} />
                </IconButton>

                {searchQuery && (
                    <div>
                    {filteredChannel.map(channel => (
                        <div key={channel.id}><Link href={`/channel-lobby`} onClick={(() => data.setItem("channelId", channel.id))}>{channel.name}</Link></div>
                    ))}
                </div>
                )}
            </div>
        </>
    );
}
