package com.chat.channel;

import com.chat.chat.ChatApplication;
import com.chat.chat.channel.Channel;
import com.chat.chat.channel.ChannelRepository;
import com.chat.chat.channel.ChannelService;
import com.chat.chat.customExceptions.ChannelNameAlreadyTakenException;
import com.chat.chat.customExceptions.InvalidChannelNameLengthException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = ChatApplication.class)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ChannelServiceTest {

    @Autowired
    protected ChannelService service;

    @Autowired
    protected ChannelRepository repo;

    protected ArrayList<Long> channelIds = new ArrayList<>();

    private Channel room1;
    private Channel room2;


    @BeforeEach
    void setupChannels() {
        room1 = service.createChannel("room1");
        room2 = service.createChannel("room2");
        channelIds.add(room1.getId());
        channelIds.add(room2.getId());
    }

    @AfterEach
    void cleanUpChannels() {
        for (Long id : channelIds) {
            service.deleteChannelById(id);
        }
    }

    @Test
    void testCreateChannel() {
        String expected = "test-channel";
        Channel channel = service.createChannel("test-channel");
        assertNotNull(channel);
        assertEquals(expected, channel.getName());
    }

    @Test
    void testCreateChannelHasUUID() {
        Channel channelUuid = service.createChannel("testing");
        String uuid = channelUuid.getLink();
        assertEquals(36, uuid.length());
    }

    @Test
    void testCreateChannelRemoveWhiteSpace() {
        String expected = "test";
        Channel channel = service.createChannel("    test    ");
        assertEquals(expected, channel.getName());
    }

    @Test
    void testCreateChannelReturnsChannelNameAlreadyTakenException() {
        Exception exception = assertThrows(ChannelNameAlreadyTakenException.class, () -> service.createChannel(room1.getName()));
        String expected = "Channel name 'room1' is already taken.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testCreateChannelReturnsExceptionLengthGreaterThan30() {
        Exception exception = assertThrows(InvalidChannelNameLengthException.class, () -> service.createChannel("this-channel-name-is-really-really-long-and-should-throw"));
        String expected = "Channel name must be 30 characters or less";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testCreateChannelReturnsExceptionLengthLessThan5() {
        Exception exception = assertThrows(InvalidChannelNameLengthException.class, () -> service.createChannel("room"));
        String expected = "Channel name must be at least 5 characters";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testCreateChannelName30CharactersShouldSucceed() {
        Channel channel = service.createChannel("abcdefghijklmnopqrstuvwxyztttt");
        assertNotNull(channel);
    }

    @Test
    void testCreateChannelName5CharactersShouldSucceed() {
        Channel channel = service.createChannel("fives");
        assertNotNull(channel);
    }

    @Test
    void testDeleteChannelByIdThrowsException() {
        Channel deleteChannel = service.createChannel("delete-me");
        service.deleteChannelById(deleteChannel.getId());
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getChannelById(deleteChannel.getId()));
        String expected = String.format("Channel with id '%s' does not exist", deleteChannel.getId());
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteChannel() {
        Channel channel = service.createChannel("delete");
        service.deleteChannelById(channel.getId());
        Optional<Channel> isDeleted = repo.findById(channel.getId());
        assertFalse(isDeleted.isPresent());
    }

    @Test
    void testGetChannelByChannelName() {
        Channel channel = service.getChannelByChannelName(room2.getName());
        assertNotNull(channel);
    }

    @Test
    void testGetChannelByChannelNameShouldThrowNoSuchElementException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getChannelByChannelName("room-test"));
        String expected = String.format("Channel with name '%s' does not exist", "room-test");
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteChannelByIdShouldThrowExceptionNoSuchElement() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.deleteChannelById(255L));
        String expected = String.format("Channel with id '%s' does not exist", 255L);
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }
}













