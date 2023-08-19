# ChatHub: Real-time Messaging Application
ChatHub is a messaging application built using an array of modern technologies to provide a seamless chatting experience. With ChatHub, you can create your own chat channel or join existing ones with ease. Share the chat experience with your friends using unique channel links!

https://github.com/vwingardh/ChatHub/assets/101557392/6dbf71e8-630f-496d-ada8-a0d0ae6316c8

## Functionality
* **Username Creation:** Create a unique username to kick-start your ChatHub journey.
* **Create Channels:** Start your own conversation by creating a chat channel.
* **Join Channels:** Either search for an existing channel or join via a shared channel link.
* **Real-time Messaging:** Experience seamless conversations with WebSockets.
* **Persistent Messaging:** Never lose a message. All your conversations are saved to the database.
* **Active Users Tracker:** Redis-backed active user tracking.

## Teach Stack
* **Backend:** Java, Spring Framework
* **Frontend:** Next.js, HTML, CSS, Material UI
* **Database:** PostgreSQL for persistence, Redis and Lettuce for active user tracking
* **Real-time Communication:** WebSockets, SockJS
* **Containerization:** Docker

## Getting Started
### Prerequisites
* Docker
* Java
* Node.js & npm

#### Steps to Setup
1. Clone the repository:
```
git clone https://github.com/vwingardh/ChatHub.git
cd ChatHub
```

2. Start the Docker services:
Make sure you have Docker running.
```
docker-compose up -d
```

3. Backend Setup:
Navigate to the backend directory and run the Spring Boot application:
```
cd backend
./mvnw spring-boot:run
```

4. Frontend Setup:
Navigate to the frontend directory and install the dependencies:
```
cd frontend
npm install
```

5. Start the Next.js server: 
```
npm run dev
```

6. Done! The application should now be running at http://localhost:3000.
