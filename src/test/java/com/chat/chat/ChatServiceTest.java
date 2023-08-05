package com.chat.chat;

import com.chat.chat.customExceptions.InvalidUsernameLengthException;
import com.chat.chat.user.ChatUser;
import com.chat.chat.user.ChatUserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ChatServiceTest {

    @Autowired
    ChatUserService service;

    @BeforeEach
    void setupUsers() {
        service.createUser("test1");
        service.createUser("test2");
        service.createUser("test3");
    }

    @AfterEach
    void cleanUpUsers() {
        service.deleteUserById(1L);
        service.deleteUserById(2L);
        service.deleteUserById(3L);
    }

    @Test
    void testCreateUser() {
        String expected = "test-user";
        ChatUser createdUser = service.createUser("test-user");
        assertNotNull(createdUser);
        assertEquals(expected, createdUser.getUsername());
    }

    @Test
    void testCreateUserLessThan5CharactersThrowsException() {
        Exception exception = assertThrows(InvalidUsernameLengthException.class, () -> service.createUser("Test_driven_development_is_fun!"));
        String expected = "Username must be less than 30 characters";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testCreateUserGreaterThan50CharactersThrowsException() {
        Exception exception = assertThrows(InvalidUsernameLengthException.class, () -> service.createUser("four"));
        String expected = "Username must be at least 5 characters";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteUserIsDeleted() {
        ChatUser deletedUser = service.createUser("delete-user");
        service.deleteUserById(deletedUser.getId());
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getUserById(deletedUser.getId()));
        String expected = "User with id '4' does not exist";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteUserThrowsException() {
        ChatUser deletedUser = service.createUser("delete-user");
        service.deleteUserById(deletedUser.getId());
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.deleteUserById(deletedUser.getId()));
        String expected = "User with id '4' does not exist";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testGetUserById() {
        Long expected = service.getUserByUsername("test3").getId();
        Long actual = service.getUserById(expected).getId();
        assertEquals(expected, actual);
    }

    @Test
    void testGetUserByIdThrowNoSuchElementException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getUserById(4L));
        String expected = "User with id '4' does not exist";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testGetUserByUsername() {
        String expected = "test2";
        String actual = service.getUserByUsername("test2").getUsername();
        assertEquals(expected, actual);
    }

    @Test
    void testGetUserByUsernameThrowNoSuchElementException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getUserByUsername("name"));
        String expected = "User with username 'name' does not exist";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testGetAllUsers() {
        List<ChatUser> users = service.getAllUsers();
        assertEquals(3, users.size());
    }

}
