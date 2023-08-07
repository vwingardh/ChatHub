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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ChatServiceTest {

    @Autowired
    protected ChatUserService service;

    protected ArrayList<Long> userIds = new ArrayList<>();


    @BeforeEach
    void setupUsers() {
        ChatUser user1 = service.createUser("test1");
        ChatUser user2 = service.createUser("test2");
        ChatUser user3 = service.createUser("test3");
        userIds.add(user1.getId());
        userIds.add(user2.getId());
        userIds.add(user3.getId());
    }

    @AfterEach
    void cleanUpUsers() {
        for (Long id : userIds) {
            service.deleteUserById(id);
        }
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
        String expected = "Username must be 30 characters or less";
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
    void testCreateUser5CharactersShouldSucceed() {
        ChatUser user = service.createUser("fives");
        assertNotNull(user);
    }

    @Test
    void testCreateUserExactly30CharactersShouldSucceed() {
        ChatUser user = service.createUser("abcdefghijklmnopqrstuvwxyztttt");
        assertNotNull(user);
    }

    @Test
    void testDeleteUserIsDeleted() {
        ChatUser deletedUser = service.createUser("delete-user");
        service.deleteUserById(deletedUser.getId());
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getUserById(deletedUser.getId()));
        String expected = String.format("User with id '%s' does not exist", deletedUser.getId());
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteUserThrowsException() {
        ChatUser deletedUser = service.createUser("delete-user");
        service.deleteUserById(deletedUser.getId());
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.deleteUserById(deletedUser.getId()));
        String expected = String.format("User with id '%s' does not exist", deletedUser.getId());
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
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getUserById(255L));
        String expected = "User with id '255' does not exist";
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
