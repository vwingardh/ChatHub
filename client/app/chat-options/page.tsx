import Link from "next/link";


export default function ChatOptions() {

    return (
        <main>
            <Link href={"/create-chatroom"} style={{ color: 'red' }}>create chatroom</Link>
            <Link href={"/join-chatroom"} style={{ color: 'blue' }}>join chatroom</Link>
        </main>
    )
}