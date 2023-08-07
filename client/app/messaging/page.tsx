"use client";
import { useState } from "react";
import DisplayMessage from "../components/DisplayMessage";
import MessageForm from "../components/MessageForm";

export default function ChatroomMessaging() {

    const [messageTextError, setMessageTextError] = useState("");
    const [messageId, setMessageId] = useState("");

    return (
        <main>
            <DisplayMessage messageId={messageId} />
            <MessageForm setMessageTextError={setMessageTextError} setMessageId={setMessageId}/>
            {messageTextError && <div>{messageTextError}</div>}
        </main>
    )
}