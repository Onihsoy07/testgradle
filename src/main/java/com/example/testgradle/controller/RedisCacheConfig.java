package com.example.testgradle.controller;

import java.lang.annotation.Annotation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisCacheConfig implements CacheConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() { return new LettuceConnectionFactory(host, port); }

    @Override
    public String[] cacheNames() {
        return new String[0];
    }

    @Override
    public String keyGenerator() {
        return null;
    }

    @Override
    public String cacheManager() {
        return null;
    }

    @Override
    public String cacheResolver() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
