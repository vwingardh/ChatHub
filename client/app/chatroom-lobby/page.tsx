"use client";
import { useEffect, useState } from "react";
import DisplayMessage from "../components/DisplayMessage";
import axios from "axios";
import { useRouter } from "next/router";
import MessageForm from "../components/MessageForm";


export default function ChatroomLobby() {

    const [messageTextError, setMessageTextError] = useState("");
    const [messageId, setMessageId] = useState("");

    return (
        <main>
            <DisplayMessage messageId={messageId} joinLink={null} />
            <MessageForm setMessageTextError={setMessageTextError} setMessageId={setMessageId}/>
            {messageTextError && <div>{messageTextError}</div>}
        </main>
    )
}