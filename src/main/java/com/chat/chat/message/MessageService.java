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
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepo;
    private final ChannelRepository chatRepo;
    private final ChatUserRepository userRepo;

    @Autowired
    public MessageService(MessageRepository messageRepo, ChannelRepository chatRepo, ChatUserRepository userRepo) {
        this.messageRepo = messageRepo;
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
    }

    public Message createMessage(String text, Long userId, Long channelId) throws MessageExceedsMaximumException {
        if (text.length() >= 501) {
            throw new MessageExceedsMaximumException();
        }
        Optional<ChatUser> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new NoSuchElementException(String.format("User with id '%s' does not exist", userId));
        }
        Optional<Channel> channel = chatRepo.findById(channelId);
        if (!channel.isPresent()) {
            throw new NoSuchElementException(String.format("Channel with id '%s' does not exist", channelId));
        }
        Message message = new Message();
        message.setMessageText(text);
        message.setChannel(chatRepo.findById(channelId).get());
        message.setSender(userRepo.findById(userId).get());
        return messageRepo.save(message);
    }

    public void deleteMessageById(Long id) throws NoSuchElementException {
        Optional<Message> message = messageRepo.findById(id);
        if (!message.isPresent()) {
            throw new NoSuchElementException(String.format("Message with id '%s' does not exist", id));
        }
        messageRepo.deleteById(id);
    }

    public Message getMessageById(Long id) throws NoSuchElementException {
        Optional<Message> message = messageRepo.findById(id);
        if (!message.isPresent()) {
            throw new NoSuchElementException(String.format("Message with id '%s' does not exist", id));
        }
        return message.get();
    }

    public List<Message> getMessagesByUserIdChannelId(Long userId, Long channelId) throws NoSuchElementException {
        Optional<ChatUser> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new NoSuchElementException(String.format("User with id '%s' does not exist", userId));
        }
        Optional<Channel> channel = chatRepo.findById(channelId);
        if (!channel.isPresent()) {
            throw new NoSuchElementException(String.format("Channel with id '%s' does not exist", channelId));
        }
        List<Message> messages = messageRepo.findMessageBySenderAndChannel(user.get(), channel.get());
        return messages;
    }

    public List<Message> getMessagesByChannelId(Long channelId) throws NoSuchElementException {
        Optional<Channel> channel = chatRepo.findById(channelId);
        if (!channel.isPresent()) {
            throw new NoSuchElementException(String.format("Channel with id '%s' does not exist", channelId));
        }
        List<Message> messageList = messageRepo.findMessageByChannel(channel.get());
        return messageList;
    }

}
