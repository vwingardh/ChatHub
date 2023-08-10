import Link from "next/link";


export default function ChatOptions() {

    return (
        <main>
            <Link href={"/create-channel"} style={{ color: 'red' }}>create channel</Link>
            <Link href={"/join-channel"} style={{ color: 'blue' }}>join channel</Link>
        </main>
    )
}