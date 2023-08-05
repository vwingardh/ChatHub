//package com.chat.chat;
//
//import com.chat.chat.chat.*;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.checkerframework.checker.units.qual.A;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ChatControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private UserRepository chatRepo;
//
//    @Autowired
//    private ChatUserController controller;
//
//
//    @Test
//    public void testGetStatusCode200() {
//        ResponseEntity expected = ResponseEntity.ok().build();
//
//        ResponseEntity actual = controller.getStatus200();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testGetUserFromCreateUser() throws Exception {
//        ChatUser chatUser = new ChatUser("1", "vileshocka");
//
//        mockMvc.perform(post("/users")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(chatUser)))
//                .andExpect(status().isOk());
//
//        assertTrue(chatRepo.findById(chatUser.getId()).isPresent());
//    }
//}
