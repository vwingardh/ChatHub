"use client";
import { Button } from "@mui/material";
import Link from "next/link";

export default function ChatButton() {
    
    const data = sessionStorage;
    const userId = data.getItem("userId");

    return (
        <main className="flex min-h-screen flex-col items-center justify-between p-24">
            {!userId ? (
                <Link href="/user">
                    <Button>Chat Now</Button>
                </Link>
            ) : (
                <Link href="/chat-options">
                    <Button>Chat Now</Button>
                </Link>
            )}
        </main>
    );
}
