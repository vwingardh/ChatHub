"use client";
import { Container, Grid, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import Link from "next/link";

type ChannelProps = {
    id: string;
    name: string;
};

export default function SearchChannels() {
    const [searchQuery, setSearchQuery] = useState("");
    const [retrievedChannel, setRetrievedChannels] = useState<
        Array<ChannelProps>
    >([]);

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
        <Container className="container--search-form">
            <Grid container className="form">
                <Grid item xs={12}>
                    <TextField
                        id="search-bar"
                        className="text"
                        onInput={(e: React.ChangeEvent<HTMLInputElement>) => {
                            setSearchQuery(e.target.value);
                        }}
                        label="Search channels"
                        variant="outlined"
                        placeholder="Search..."
                        size="small"
                        fullWidth
                    />
                </Grid>

                <Grid item xs={12}>
                    {searchQuery && (
                        <div className="search__results">
                            {filteredChannel.map((channel) => (
                                <div key={channel.id} className="search__links">
                                    <Link
                                        href={`/channel-lobby`}
                                        onClick={() => {
                                            data.setItem(
                                                "channelId",
                                                channel.id
                                            );
                                            data.setItem(
                                                "channelName",
                                                channel.name
                                            );
                                        }}
                                    >
                                        {channel.name}
                                    </Link>
                                </div>
                            ))}
                        </div>
                    )}
                </Grid>
            </Grid>
        </Container>
    );
}
