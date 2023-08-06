import { CssBaseline } from "@mui/material";
import "./globals.css";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import React from "react";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Chat application",
  description: "Create a username, create a chat room, and start chatting!",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <head>
        <meta charSet="UTF-8" />
        <meta name="viewport" content="initial-scale=1, width=device-width" />
        <title>Chat</title>
        <link rel="icon" type="image/x-icon" href="/" />
      </head>
      <React.Fragment>
        <CssBaseline />
        <body className={inter.className}>{children}</body>
      </React.Fragment>
    </html>
  );
}
