package com.chat.channel;

import com.chat.chat.ChatApplication;
import com.chat.chat.channel.ChannelService;
import com.chat.chat.channel.ChannelController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ChannelController.class)
@ContextConfiguration(classes = ChatApplication.class)
public class ChannelControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected ChannelService service;


    @Test
    void testGetStatusCode200() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/channel-status"))
                .andExpect(status().isOk());
    }


}
