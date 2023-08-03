package com.chat.chat;

import com.chat.chat.user.ChatUser;
import com.chat.chat.user.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    private final UserController controller = new UserController();

    @Test
    public void getStatusCode200() {
        ResponseEntity expected = ResponseEntity.ok().build();

        ResponseEntity actual = controller.getStatus200();

        assertEquals(expected, actual);
    }

    @Test
    public void getUserFromCreateUser() {
        ChatUser chatUser = new ChatUser(1, "vileshocka");

        int expected = chatUser.getId();

        ChatUser actual = controller.createUser();
        int actualId = actual.getId();

        assertEquals(expected, actualId);
    }
}
