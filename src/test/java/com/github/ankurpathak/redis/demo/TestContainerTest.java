package com.github.ankurpathak.redis.demo;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.BindMode;


public class TestContainerTest {

    @ClassRule
    public static RedisContainer redis = new RedisContainer()
            .withClasspathResourceMapping("redis.conf", "/usr/local/etc/redis/redis.conf", BindMode.READ_ONLY)
            .withCommand("redis-server","/usr/local/etc/redis/redis.conf");

    @Test
    public void contextLoads() {
    }

}
