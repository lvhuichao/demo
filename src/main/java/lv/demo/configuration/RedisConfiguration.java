package lv.demo.configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;

public class RedisConfiguration {

    public static final JedisPooled jedis = new JedisPooled("localhost", 6379);

    public static JedisPooled getInstance() {
        return jedis;
    }
}
