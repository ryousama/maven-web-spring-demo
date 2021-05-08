package com.study.boot.redis.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author sakura
 * @Create 2021-05-07 15:06
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory){
        // 新建 RedisTemplate 对象，key 为 String 对象，value 为 Serializable（可序列化的）对象
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        // 传入连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);

        // key 值使用字符串序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value 值使用 json 序列化器 fastJson有安全漏洞
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 返回 redisTemplate 对象
        return redisTemplate;
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

}
