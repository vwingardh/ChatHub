package com.chat.message;

import com.chat.chat.ChatApplication;
import com.chat.chat.customExceptions.MessageExceedsMaximumException;
import com.chat.chat.message.Message;
import com.chat.chat.message.MessageService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = ChatApplication.class)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MessageServiceTest {

    @Autowired
    protected MessageService service;

    protected ArrayList<Long> messageIds = new ArrayList<>();

    @BeforeEach
    void setupMessages() {
        Message message1 = service.createMessage("This is text1");
        Message message2 = service.createMessage("This is text2");
        Message message3 = service.createMessage("This is text3");
        messageIds.add(message1.getId());
        messageIds.add(message2.getId());
        messageIds.add(message3.getId());
    }

    @AfterEach
    void cleanUpMessages() {
        for (Long id : messageIds) {
            service.deleteMessageById(id);
        }
    }

    @Test
    void testCreateMessage() {
        String expected = "this is a test";
        Message message = service.createMessage("this is a test");
        assertNotNull(message);
        assertEquals(expected, message.getMessageText());
    }

    @Test
    void testDeleteMessageById() {
        Message message = service.createMessage("delete-me");
        service.deleteMessageById(message.getId());
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.deleteMessageById(message.getId()));
        String expected = String.format("Message with id '%s' does not exist", message.getId());
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testCreateMessageThrowsMessageExceedsMaximumException() {
        Exception exception = assertThrows(MessageExceedsMaximumException.class, () -> service.createMessage("In the vast expanse of the universe, where stars twinkle with an unparalleled brilliance and planets orbit with unwavering determination, there exists a small blue planet known as Earth, teeming with life and brimming with endless mysteries. From its azure oceans that cover its surface to its soaring peaks that touch the sky, Earth's beauty is boundless, and its stories, as old as time itself, are whispered by the winds, written in the sands, and echoed in the heartbeats of all the creatures that call it home."));
        String expected = "Text exceeds 500 characters";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testCreateMessageShouldNotThrowMessageExceedsMaximumException() {
        Message message = service.createMessage("In the vast expanse of the universe, where stars twinkle with an unparalleled brilliance and planets orbit with unwavering determination, there exists a small blue planet known as Earth, teeming with life and brimming with endless mysteries. From its azure oceans that cover its surface to its soaring peaks that touch the sky, Earth's beauty is boundless, and its stories, as old as time itself, are whispered by the winds, written in the sands, and echoed in the heartbeats of all the creatures tha");
        assertNotNull(message);
    }

}







