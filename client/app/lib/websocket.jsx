import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

let stompClient;
let activeUsersSubscription;

export const connect = (channelId, onMessageReceived, onActiveUsersReceived) => {
    if (stompClient && stompClient.connected) {
        console.warn("WebSocket is already connected.");
        return;
    }

    const socket = new SockJS('http://localhost:8080/ws');
    stompClient = Stomp.over(socket);

    const headers = {
        "channelId": channelId.toString()
    };

    stompClient.connect(headers, () => {
        stompClient.subscribe(`/channel/${channelId}`, onMessageReceived);

        if (onActiveUsersReceived) {
            subscribeToActiveUsers(channelId, onActiveUsersReceived);
        }
    }, 
    (error) => {
        console.error('WebSocket Connection Error:', error);
    });
}

export const subscribeToActiveUsers = (channelId, onActiveUsersReceived) => {
    if (stompClient && stompClient.connected) {
        activeUsersSubscription = stompClient.subscribe(`/active-users/${channelId}`, onActiveUsersReceived);
    } else {
        console.error("Stomp client not connected!");
    }
}

export const unsubscribeFromActiveUsers = () => {
    if (activeUsersSubscription) {
        activeUsersSubscription.unsubscribe();
    }
}

export const sendMessage = (messageText, userId, channelId) => {
    const message = {
        messageText: messageText,
        userId: userId,
        channelId: channelId
    }
    stompClient.send(`/app/channel/${message.channelId}`, {}, JSON.stringify(message));
}


export const disconnect = () => {
    unsubscribeFromActiveUsers();
    if (stompClient && stompClient.connected) {
        stompClient.disconnect();
    }
}