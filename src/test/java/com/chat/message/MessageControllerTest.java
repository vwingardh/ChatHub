package com.chat.message;

import com.chat.chat.ChatApplication;
import com.chat.chat.message.MessageController;
import com.chat.chat.message.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MessageController.class)
@ContextConfiguration(classes = ChatApplication.class)
public class MessageControllerClass {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected MessageService service;

    @Test
    void testGetStatusCode200() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/message-status"))
                .andExpect(status().isOk());
    }
}
