package com.chat.chat;

import com.chat.chat.user.ChatUser;
import com.chat.chat.user.ChatUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ChatServiceTest {

    @Autowired
    ChatUserService service;

    @BeforeEach
    void createUsers() {
        ChatUser user1 = service.createUser("test1");
        ChatUser user2 = service.createUser("test2");
        ChatUser user3 = service.createUser("test3");
    }

    @Test
    void getAllUsers() {
        List<ChatUser> users = this.service.getAllUsers();
        assertEquals(3, users.size());
    }

}
