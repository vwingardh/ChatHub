"use client";
import { Button } from "@mui/material";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/lab/Alert";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

type ChannelNameProps = {
    channelName: string;
    setChannelNameError: React.Dispatch<React.SetStateAction<string>>;
};

export default function ChannelNameSubmitButton({
    channelName,
    setChannelNameError,
}: ChannelNameProps) {
    const router = useRouter();
    const [open, setOpen] = useState(false);
    const [link, setLink] = useState<string | null>(null);
    const [isCopied, setIsCopied] = useState(false);
    const data = sessionStorage;

    const handleCloseSnackbar = () => {
        setOpen(false);
        router.push("/channel-lobby");
    };

    const handleChannelNameSubmit = () => {
        axios
            .post("http://localhost:8080/api/channels", {
                channelName: channelName,
            })
            .then((response) => {
                setLink(response.data.data.link);
                data.setItem("channelId", response.data.data.id);
                data.setItem("channelName", response.data.data.name);
                setOpen(true);
            })
            .catch((error) => {
                if (
                    error.response &&
                    error.response.data &&
                    error.response.data.error
                ) {
                    setChannelNameError(error.response.data.error);
                } else {
                    setChannelNameError("An unexpected error occurred.");
                }
                console.error(error);
            });
    };

    const handleLinkHover = () => {
        if (link) {
            navigator.clipboard
                .writeText(link)
                .then(() => {
                    setIsCopied(true);
                    setTimeout(() => setIsCopied(false), 6000);
                })
                .catch((err) => console.error("Could not copy text: ", err));
        }
    };

    return (
        <>
            <Snackbar
                open={open}
                onClose={handleCloseSnackbar}
                anchorOrigin={{ vertical: "top", horizontal: "center" }}
            >
                <Alert
                    onClose={handleCloseSnackbar}
                    severity="info"
                    sx={{ width: "100%" }}
                >
                    Share this link for others to join:
                    <div
                        onMouseEnter={handleLinkHover}
                        style={{
                            cursor: "pointer",
                            textDecoration: "underline",
                        }}
                    >
                        {isCopied ? "Copied!" : link}
                    </div>
                </Alert>
            </Snackbar>

            <Button
                onClick={handleChannelNameSubmit}
                className="button--submit"
            >
                Create
            </Button>
        </>
    );
}
