package com.iron.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @auther zzyy
 * @create 2022-12-14 20:50
 */
@Configuration
public class RedisConfig
{
    @Autowired
    RedisProperties redisProperties;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式json，使用GenericJackson2JsonRedisSerializer替换默认序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean(name = "redissonClient1")
    RedissonClient redissonClient1() {
        Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://192.168.11.100:6379")
                .setDatabase(0)
                .setPassword("111111");

        return Redisson.create(config);
    }

    @Bean(name = "redissonClient2")
    RedissonClient redissonClient2() {
        Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://192.168.11.102:6380")
                .setDatabase(0)
                .setPassword("111111");

        return Redisson.create(config);
    }

    @Bean(name = "redissonClient3")
    RedissonClient redissonClient3() {
        Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://192.168.11.103:6381")
                .setDatabase(0)
                .setPassword("111111");

        return Redisson.create(config);
    }

}

