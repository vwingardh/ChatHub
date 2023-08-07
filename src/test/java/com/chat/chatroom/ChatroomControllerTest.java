package com.chat.chatroom;

import com.chat.chat.ChatApplication;
import com.chat.chat.chatroom.ChatroomService;
import com.chat.chat.chatroom.ChatroomController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ChatroomController.class)
@ContextConfiguration(classes = ChatApplication.class)
public class ChatroomControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected ChatroomService service;


    @Test
    void testGetStatusCode200() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/chatroom-status"))
                .andExpect(status().isOk());
    }


}
