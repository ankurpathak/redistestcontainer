package com.github.ankurpathak.redis.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.testcontainers.containers.GenericContainer;

public class RedisContainer extends GenericContainer<RedisContainer> implements TestRule {

    /**
     * This is the internal port on which MongoDB is running inside the container.
     * <p>
     * You can use this constant in case you want to map an explicit public port to it
     * instead of the default random port. This can be done using methods like
     * {@link #setPortBindings(java.util.List)}.
     */
    public static final int REDIS_PORT = 6379;
    public static final String DEFAULT_IMAGE_AND_TAG = "redis:5.0.5";

    /**
     * Creates a new {@link RedisContainer} with the {@value DEFAULT_IMAGE_AND_TAG} image.
     */
    public RedisContainer() {
        this(DEFAULT_IMAGE_AND_TAG);
    }

    /**
     * Creates a new {@link RedisContainer} with the given {@code 'image'}.
     *
     * @param image the image (e.g. {@value DEFAULT_IMAGE_AND_TAG}) to use
     */
    public RedisContainer(@NotNull String image) {
        super(image);
        addExposedPort(REDIS_PORT);
        //withClasspathResourceMapping("redis.conf",
          //      "/usr/local/etc/redis/redis.conf",
        //        BindMode.READ_ONLY);
       // withCommand("redis-server  /usr/local/etc/redis/redis.conf");
    }

    /**
     * Returns the actual public port of the internal MongoDB port ({@value REDIS_PORT}).
     *
     * @return the public port of this container
     * @see #getMappedPort(int)
     */
    @NotNull
    public Integer getPort() {
        return getMappedPort(REDIS_PORT);
    }


    @Override
    @SuppressWarnings("deprecation")
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                start();
                try {
                    statement.evaluate();
                }finally {
                  // close();
                }
            }
        };
    }

}
