"use client";
import { Button } from "@mui/material";
import axios from "axios";

type UsernameProps = {
  username: string;
};

export default function UsernameSubmitButton(username: UsernameProps) {
  const handleUsernameSubmit = () => {
    axios
      .post(
        "http://localhost:8080/api/users",
        {
          "username": username.username,
        }
      )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <>
      <Button onClick={handleUsernameSubmit}>Create</Button>
    </>
  );
}
