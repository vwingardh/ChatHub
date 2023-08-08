package com.chat.message;

import com.chat.chat.ChatApplication;
import com.chat.chat.chatroom.Chatroom;
import com.chat.chat.chatroom.ChatroomService;
import com.chat.chat.customExceptions.MessageExceedsMaximumException;
import com.chat.chat.message.Message;
import com.chat.chat.message.MessageRepository;
import com.chat.chat.message.MessageService;
import com.chat.chat.user.ChatUser;
import com.chat.chat.user.ChatUserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = ChatApplication.class)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MessageServiceTest {

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected ChatUserService chatUserService;

    @Autowired
    protected ChatroomService chatroomService;

    @Autowired
    protected MessageRepository messageRepo;

    protected ArrayList<Long> messageIds = new ArrayList<>();

    private ChatUser user1;
    private ChatUser user2;
    private ChatUser user3;

    private Chatroom chatroom1;
    private Chatroom chatroom2;
    private Chatroom chatroom3;

    private Message message1;
    private Message message2;
    private Message message3;

    @BeforeEach
    void setupMessages() {
        user1 = chatUserService.createUser("user1");
        user2 = chatUserService.createUser("user2");
        user3 = chatUserService.createUser("user3");

        chatroom1 = chatroomService.createChatroom("chatroom1");
        chatroom2 = chatroomService.createChatroom("chatroom2");
        chatroom3 = chatroomService.createChatroom("chatroom3");

        message1 = messageService.createMessage("This is text1", user1.getId(), chatroom1.getId());
        message2 = messageService.createMessage("This is text2", user2.getId(), chatroom2.getId());
        message3 = messageService.createMessage("This is text3", user3.getId(), chatroom3.getId());

        messageIds.add(message1.getId());
        messageIds.add(message2.getId());
        messageIds.add(message3.getId());
    }

    @AfterEach
    void cleanUpMessages() {
        for (Long id : messageIds) {
            messageService.deleteMessageById(id);
        }
    }

    @Test
    void testCreateMessage() {
        String expected = "this is a test";
        Message message = messageService.createMessage("this is a test", user1.getId(), chatroom1.getId());
        assertNotNull(message);
        assertEquals(expected, message.getMessageText());
    }

    @Test
    void testDeleteMessageById() {
        ChatUser user = chatUserService.createUser("test-user");
        Chatroom chatroom = chatroomService.createChatroom("test-chatroom");
        Message message = messageService.createMessage("this is a test", user.getId(), chatroom.getId());
        messageService.deleteMessageById(message.getId());
        Optional<Message> deletedMessage = messageRepo.findById(message.getId());
        assertFalse(deletedMessage.isPresent());
    }

    @Test
    void testDeleteMessageByIdThrowsNoIdException() {
        String expected = "Message with id '255' does not exist";
        Exception exception = assertThrows(NoSuchElementException.class, () -> messageService.deleteMessageById(255L));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testCreateMessageWithUserIdDoesNotExistThrowsException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> messageService.createMessage("text",255L, chatroom2.getId()));
        String expected = "User with id '255' does not exist";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testCreateMessageWithChatroomIdDoesNotExistThrowsException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> messageService.createMessage("text", user2.getId(), 255L));
        String expected = "Chatroom with id '255' does not exist";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testCreateMessageThrowsMessageExceedsMaximumException() {
        Exception exception = assertThrows(MessageExceedsMaximumException.class, () -> messageService.createMessage("In the vast expanse of the universe, where stars twinkle with an unparalleled brilliance and planets orbit with unwavering determination, there exists a small blue planet known as Earth, teeming with life and brimming with endless mysteries. From its azure oceans that cover its surface to its soaring peaks that touch the sky, Earth's beauty is boundless, and its stories, as old as time itself, are whispered by the winds, written in the sands, and echoed in the heartbeats of all the creatures that call it home.", user3.getId(), chatroom3.getId()));
        String expected = "Text exceeds 500 characters";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testCreateMessageShouldNotThrowMessageExceedsMaximumException() {
        Message message = messageService.createMessage("In the vast expanse of the universe, where stars twinkle with an unparalleled brilliance and planets orbit with unwavering determination, there exists a small blue planet known as Earth, teeming with life and brimming with endless mysteries. From its azure oceans that cover its surface to its soaring peaks that touch the sky, Earth's beauty is boundless, and its stories, as old as time itself, are whispered by the winds, written in the sands, and echoed in the heartbeats of all the creatures tha", user3.getId(), chatroom3.getId());
        assertNotNull(message);
    }

    @Test
    void testGetMessageById() {
        Message getNewMessage = messageService.getMessageById(message3.getId());
        assertEquals(message3.getId(), getNewMessage.getId());
    }

    @Test
    void testUserIdConnectedToMessage() {
        Long expected = user2.getId();
        ChatUser actual = message2.getSender();
        assertEquals(expected, actual.getId());
    }

    @Test
    void testChatroomIdConnectedToMessage() {
        Long expected = chatroom3.getId();
        Chatroom actual = message3.getChatroom();
        assertEquals(expected, actual.getId());
    }

    @Test
    void testGetMessageByIdThrowsNoSuchElementException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> messageService.getMessageById(255L));
        String expected = String.format("Message with id '%s' does not exist", 255L);
        assertEquals(expected, exception.getMessage());
    }

}







