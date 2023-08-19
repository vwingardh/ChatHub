package com.chat.chat.message;

import com.chat.chat.channel.Channel;
import com.chat.chat.channel.ChannelRepository;
import com.chat.chat.customExceptions.MessageExceedsMaximumException;
import com.chat.chat.user.ChatUser;
import com.chat.chat.user.ChatUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepo;
    private final ChannelRepository channelRepo;
    private final ChatUserRepository userRepo;

    @Autowired
    public MessageService(MessageRepository messageRepo, ChannelRepository channelRepo, ChatUserRepository userRepo) {
        this.messageRepo = messageRepo;
        this.channelRepo = channelRepo;
        this.userRepo = userRepo;
    }

    public Message createMessage(String text, Long userId, Long channelId) throws MessageExceedsMaximumException {
        if (text.length() >= 501) {
            throw new MessageExceedsMaximumException();
        }
        ChatUser user = findUserByIdOrThrow(userId);
        Channel channel = findChannelByIdOrThrow(channelId);

        Message message = new Message();
        message.setMessageText(text);
        message.setChannel(channel);
        message.setSender(user);
        return messageRepo.save(message);
    }

    public void deleteMessageById(Long messageId) throws NoSuchElementException {
        Message message = findMessageByIdOrThrow(messageId);
        messageRepo.deleteById(message.getId());
    }

    public Message getMessageById(Long messageId) throws NoSuchElementException {
        return findMessageByIdOrThrow(messageId);
    }

    public List<Message> getMessagesByUserIdChannelId(Long userId, Long channelId) throws NoSuchElementException {
        ChatUser user = findUserByIdOrThrow(userId);
        Channel channel = findChannelByIdOrThrow(channelId);
        return messageRepo.findMessageBySenderAndChannel(user, channel);
    }

    public List<Message> getMessagesByChannelId(Long channelId) throws NoSuchElementException {
        Channel channel = findChannelByIdOrThrow(channelId);
        return messageRepo.findMessageByChannel(channel);
    }

    private ChatUser findUserByIdOrThrow(Long userId) {
        return userRepo.findById(userId).orElseThrow(() ->
                new NoSuchElementException(String.format("User with id '%s' does not exist", userId)));
    }

    private Channel findChannelByIdOrThrow(Long channelId) {
        return channelRepo.findById(channelId).orElseThrow(() ->
                new NoSuchElementException(String.format("Channel with id '%s' does not exist", channelId)));
    }

    private Message findMessageByIdOrThrow(Long messageId) {
        return messageRepo.findById(messageId).orElseThrow(() ->
                new NoSuchElementException(String.format("Message with id '%s' does not exist", messageId)));
    }

}
