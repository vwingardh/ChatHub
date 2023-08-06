import Image from 'next/image'
import ChatButton from './components/ChatButton'

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <ChatButton></ChatButton>
    </main>
  )
}
