import SockJS from 'sockjs-client';
import { Client } from 'stompjs';

let stompClient;

export const connect = (channelId, onMessageReceived) => {
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
        console.log("WebSocket connected successfully.")
        stompClient.subscribe(`/channel/${channelId}`, onMessageReceived);
        stompClient.debug = function (str) {
            console.log('STOMP: ' + str);
        };
    }, 
    (error) => {
        console.error('WebSocket Connection Error:', error);
    });
}

export const sendMessage = (messageText, userId, channelId) => {
    stompClient.debug = function (str) {
        console.log('STOMP: ' + str);
    };

    const message = {
        messageText: messageText,
        userId: userId,
        channelId: channelId
    }

    stompClient.send(`/app/channel/${message.channelId}`, {}, JSON.stringify(message));
}


export const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}