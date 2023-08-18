"use client";
import { List, ListItem } from "@mui/material";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

type ChannelProps = {
    id: string;
    name: string;
};

export default function DisplayAllChannels() {
    const [channels, setChannels] = useState<Array<ChannelProps>>([]);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/channels")
            .then((response) => {
                setChannels(response.data.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    return (
        <List className="channel-list" subheader={<li />}>
            {channels.map((channel) => (
                <ListItem key={channel.id}>
                    <Link href={`/messaging/${channel.id}`}>
                        {channel.name}
                    </Link>
                </ListItem>
            ))}
        </List>
    );
}
