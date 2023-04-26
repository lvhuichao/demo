package lv.demo;

import lv.demo.configuration.RedisConfiguration;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;

/**
 * 最简单的redis分布式锁
 * <p>
 * 缺点：
 * 1. 不可重入
 * 2. 对于redis主从架构，若从master节点获取到锁之后，master挂掉且此时slave节点尚未同步该锁信息，slave晋升为master节点，此时其他进程可以从slave节点再次获取到该锁。
 * <p>
 * 问题2可参考redLock：<a href="https://www.cnblogs.com/rgcloveyaya/p/rgc_love_yaya_1003days.html">...</a>
 */
public class RedisLock {

    /**
     * 代表临界资源
     */
    protected final String key;
    /**
     * 防止A进程获取到锁之后，过期锁删除，之后B进程获取到锁，然后A进程误删B进程的锁。
     */
    protected final String value = UUID.randomUUID().toString();

    public RedisLock(String key) {
        this.key = key;
    }

    /**
     * 原子的setnx with expire time.
     */
    public boolean tryLock() {
        SetParams setParams = SetParams.setParams().px(500).nx();
        return RedisConfiguration.getInstance().set(key, value, setParams) != null;
    }

    /**
     * lua脚本原子的先判断value是否正确，然后再执行删除。
     */
    public boolean unlock() {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        return RedisConfiguration.getInstance().eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }
}
