package com.chat.chat;

import com.chat.chat.user.ChatUserController;
import com.chat.chat.user.ChatUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ChatUserController.class)
public class ChatControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected ChatUserService service;


    @Test
    void testGetStatusCode200() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/status"))
                .andExpect(status().isOk());
    }

}
