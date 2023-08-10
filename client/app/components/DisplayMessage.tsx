type Message = {
    id: string;
    messageText: string;
}

type MessagesProps = {
    messages: Message[];
}

export default function DisplayMessage({ messages }: MessagesProps) {

    return (
        <>
            {messages.map((message) => (
                <div key={message.id}>{message.messageText}</div>
            ))}
        </>
    );
}
