import { Button } from '@mui/material';
import Link from 'next/link';


export default function ChatButton() {
    
    return (
      <main className="flex min-h-screen flex-col items-center justify-between p-24">
        <Link href={'/user'}><Button>Chat Now</Button></Link>
      </main>
    )
  }