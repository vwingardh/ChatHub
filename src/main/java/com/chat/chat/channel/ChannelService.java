package com.chat.chat.channel;

import com.chat.chat.customExceptions.ChannelNameAlreadyTakenException;
import com.chat.chat.customExceptions.InvalidChannelNameLengthException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
@Transactional
public class ChannelService {

    private final ChannelRepository repo;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public ChannelService(ChannelRepository repo, StringRedisTemplate redisTemplate) {
        this.repo = repo;
        this.redisTemplate = redisTemplate;
    }

    public Channel createChannel(String channelName) throws ChannelNameAlreadyTakenException {
        if (channelName.length() <= 4 || channelName.length() >= 31) {
            throw new InvalidChannelNameLengthException(channelName.length());
        }
        isChannelAvailableElseThrow(channelName);

        Channel channel = new Channel();
        channel.setName(channelName.trim());
        return repo.save(channel);
    }

    public void deleteChannelById(Long channelId) {
        Channel channel = findChannelByIdOrElseThrow(channelId);
        repo.deleteById(channel.getId());
    }

    public List<Channel> getAllChannels() {
        return repo.findAll();
    }

    public Channel getChannelByChannelName(String channelName) throws NoSuchElementException {
        return findChannelByChannelNameOrElseThrow(channelName);
    }

    public void getChannelById(Long channelId) throws NoSuchElementException {
        findChannelByIdOrElseThrow(channelId);
    }

    public Channel getChannelByChannelLink(String joinLink) {
        return findChannelByLinkOrElseThrow(joinLink);
    }

    public void incrementActiveUsers(Long channelId) {
        String key = "channel:" + channelId + ":activeUsers";
        redisTemplate.opsForValue().increment(key);
    }

    public void decrementActiveUsers(Long channelId) {
        String key = "channel:" + channelId + ":activeUsers";
        redisTemplate.opsForValue().decrement(key);
    }

    public Long getActiveUsers(Long channelId) {
        String key = "channel:" + channelId + ":activeUsers";
        return redisTemplate.opsForValue().increment(key, 0);
    }

    public void printAllKeys() {
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            String value = redisTemplate.opsForValue().get(key);
            System.out.println(key + ": " + value);
        }
    }

    private void isChannelAvailableElseThrow(String channelName) {
        Channel isChannelAvailable = repo.findByChannelName(channelName);
        if (isChannelAvailable != null) {
            throw new ChannelNameAlreadyTakenException(channelName);
        }
    }

    private Channel findChannelByIdOrElseThrow(Long channelId) {
            return repo.findById(channelId).orElseThrow(() ->
                    new NoSuchElementException(String.format("Channel with id '%s' does not exist", channelId)));
    }

    private Channel findChannelByChannelNameOrElseThrow(String channelName) {
        Channel channel = repo.findByChannelName(channelName);
        if (channel == null) {
            throw new NoSuchElementException(String.format("Channel with name '%s' does not exist", channelName));
        }
        return channel;
    }

    private Channel findChannelByLinkOrElseThrow(String joinLink) {
        Channel channel = repo.findByLink(joinLink);
        if (channel == null) {
            throw new NoSuchElementException(String.format("Channel with link '%s' does not exist", joinLink));
        }
        return channel;
    }

}

