import { Button, Container } from "@mui/material";
import Link from "next/link";

export default function ChatOptions() {
    const chatOptions = [
        { label: "Create Channel", href: "/create-channel" },
        {
            label: "Join Channel",
            href: "/join-channel",
        },
    ];

    return (
        <main>
            <Container className="container--standard">
                {chatOptions.map((option) => (
                    <Link key={option.label} href={option.href}>
                        <Button
                            style={{ marginBottom: "50px" }}
                            className="button--route"
                            variant="contained"
                        >
                            {option.label}
                        </Button>
                    </Link>
                ))}
            </Container>
        </main>
    );
}
