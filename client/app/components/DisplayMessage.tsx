import { Container } from "@mui/material";
import formatDateTime from "../lib/FormatDateTime";
import PersonIcon from "@mui/icons-material/Person";

type Message = {
    id: string;
    messageText: string;
    created: string;
    sender: {
        id: number;
        username: string | any;
    };
    channel: {
        id: number;
        link: string;
        name: string;
    };
};

type MessagesProps = {
    messages: Message[];
    activeUsers: number;
};

export default function DisplayMessage({
    messages,
    activeUsers,
}: MessagesProps) {
    const channelName = sessionStorage.getItem("channelName");

    return (
        <Container className="container--message-display">
            <h1>{channelName}</h1>
            <div>
                <PersonIcon className="icon--active-user"></PersonIcon>
                {activeUsers}
            </div>
            {messages.map((message) => (
                <div key={message.id} className="message">
                    <div className="message__username">
                        {message.sender.username}
                    </div>
                    <div>{message.messageText}</div>
                    <div className="message__timestamp">
                        {formatDateTime(message.created)}
                    </div>
                </div>
            ))}
        </Container>
    );
}
