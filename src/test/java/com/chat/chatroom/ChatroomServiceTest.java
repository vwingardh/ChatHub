package com.chat.chatroom;

import com.chat.chat.ChatApplication;
import com.chat.chat.chatroom.Chatroom;
import com.chat.chat.chatroom.ChatroomRepository;
import com.chat.chat.chatroom.ChatroomService;
import com.chat.chat.customExceptions.ChatroomNameAlreadyTakenException;
import com.chat.chat.customExceptions.InvalidChatroomNameLengthException;
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
public class ChatroomServiceTest {

    @Autowired
    protected ChatroomService service;

    @Autowired
    protected ChatroomRepository repo;

    protected ArrayList<Long> chatroomIds = new ArrayList<>();

    private Chatroom room1;
    private Chatroom room2;


    @BeforeEach
    void setupChatrooms() {
        room1 = service.createChatroom("room1");
        room2 = service.createChatroom("room2");
        chatroomIds.add(room1.getId());
        chatroomIds.add(room2.getId());
    }

    @AfterEach
    void cleanUpChatrooms() {
        for (Long id : chatroomIds) {
            service.deleteChatroomById(id);
        }
    }

    @Test
    void testCreateChatroom() {
        String expected = "test-chatroom";
        Chatroom chatroom = service.createChatroom("test-chatroom");
        assertNotNull(chatroom);
        assertEquals(expected, chatroom.getName());
    }

    @Test
    void testCreateChatroomRemoveWhiteSpace() {
        String expected = "test";
        Chatroom chatroom = service.createChatroom("    test    ");
        assertEquals(expected, chatroom.getName());
    }

    @Test
    void testCreateChatroomReturnsChatroomNameAlreadyTakenException() {
        Exception exception = assertThrows(ChatroomNameAlreadyTakenException.class, () -> service.createChatroom(room1.getName()));
        String expected = "Chatroom name 'room1' is already taken.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testCreateChatroomReturnsExceptionLengthGreaterThan30() {
        Exception exception = assertThrows(InvalidChatroomNameLengthException.class, () -> service.createChatroom("this-chatroom-name-is-really-really-long-and-should-throw"));
        String expected = "Chatroom name must be 30 characters or less";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testCreateChatroomReturnsExceptionLengthLessThan5() {
        Exception exception = assertThrows(InvalidChatroomNameLengthException.class, () -> service.createChatroom("room"));
        String expected = "Chatroom name must be at least 5 characters";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testCreateChatroomName30CharactersShouldSucceed() {
        Chatroom chatroom = service.createChatroom("abcdefghijklmnopqrstuvwxyztttt");
        assertNotNull(chatroom);
    }

    @Test
    void testCreateChatroomName5CharactersShouldSucceed() {
        Chatroom chatroom = service.createChatroom("fives");
        assertNotNull(chatroom);
    }

    @Test
    void testDeleteChatroomByIdThrowsException() {
        Chatroom deleteChatroom = service.createChatroom("delete-me");
        service.deleteChatroomById(deleteChatroom.getId());
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getChatroomById(deleteChatroom.getId()));
        String expected = String.format("Chatroom with id '%s' does not exist", deleteChatroom.getId());
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteChatroom() {
        Chatroom chatroom = service.createChatroom("delete");
        service.deleteChatroomById(chatroom.getId());
        Optional<Chatroom> isDeleted = repo.findById(chatroom.getId());
        assertFalse(isDeleted.isPresent());
    }

    @Test
    void testGetChatroomByChatroomName() {
        Chatroom chatroom = service.getChatroomByChatroomName(room2.getName());
        assertNotNull(chatroom);
    }

    @Test
    void testGetChatroomByChatroomNameShouldThrowNoSuchElementException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getChatroomByChatroomName("room-test"));
        String expected = String.format("Chatroom with name '%s' does not exist", "room-test");
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteChatroomByIdShouldThrowExceptionNoSuchElement() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.deleteChatroomById(255L));
        String expected = String.format("Chatroom with id '%s' does not exist", 255L);
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }
}













