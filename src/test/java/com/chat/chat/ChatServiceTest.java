//package com.chat.chat;
//
//import com.chat.chat.chat.UserRepository;
//import com.chat.chat.chat.UserService;
//import com.chat.chat.chat.ChatUser;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class ChatServiceTest {
//
//    @InjectMocks
//    UserService service;
//
//    @Mock
//    UserRepository repo;
//
//    @Test
//    private void getUsers() {
//        Mockito.doReturn(getMockUsers(1)).when(repo).findAll();
//        List<ChatUser> users = this.service.getUsers();
//        assertEquals(1, users.size());
//    }
//
//}
