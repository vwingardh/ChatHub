package com.chat.chat;

import com.chat.chat.chat.ChatService;
import com.chat.chat.chat.ChatUser;
import com.chat.chat.chat.ChatController;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatControllerTest {

    private final ChatService service = new ChatService();
    private final ChatController controller = new ChatController(service);


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
