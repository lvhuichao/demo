package lv.demo;

import com.google.common.base.Preconditions;
import lv.demo.configuration.RedisConfiguration;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * 可重入的redis分布式锁
 * <p>
 * 缺点：
 * 1. 对于redis主从架构，若从master节点获取到锁之后，master挂掉且此时slave节点尚未同步该锁信息，slave晋升为master节点，此时其他进程可以从slave节点再次获取到该锁。
 */
public class ReentrantRedisLock extends RedisLock {

    private int count;

    public ReentrantRedisLock(String key) {
        super(key);
    }

    public boolean tryLock() {
        SetParams setParams = SetParams.setParams().px(500).nx();
        if (count > 0 || RedisConfiguration.getInstance().set(key, value, setParams) != null) {
            count++;
            return true;
        }
        return false;
    }

    public boolean unlock() {
        Preconditions.checkArgument(count > 0);
        if (count > 1) {
            count--;
            return true;
        }
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        return RedisConfiguration.getInstance().eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }
}
