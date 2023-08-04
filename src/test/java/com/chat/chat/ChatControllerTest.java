import com.chat.chat.chat.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChatControllerTest {

    @Autowired
    private ChatRepository chatRepo;
    @Autowired
    private ChatService service;
    @Autowired
    private ChatController controller;

    @Test
    public void testGetStatusCode200() {
        ResponseEntity expected = ResponseEntity.ok().build();

        ResponseEntity actual = controller.getStatus200();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserFromCreateUser() {
        ChatUser chatUser = new ChatUser("1", "vileshocka");

        String expected = chatUser.getId();

        ChatUser actual = controller.createUser();
        String actualId = actual.getId();

        assertEquals(expected, actualId);
    }
}
