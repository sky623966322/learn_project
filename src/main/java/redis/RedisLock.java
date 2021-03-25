package redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * 文章分析见 https://blog.csdn.net/fly910905/article/details/87281787
 */
public class RedisLock {
    public static final String OK = "OK";

    private Jedis jedis = new Jedis();

    public boolean lock(String key, String requestId, int second){
        String set = jedis.set(key, requestId, "NX", "EX", second);
        if (OK.endsWith(set)){
            return true;
        }
        return false;
    }

    public boolean unLock(String key, String requestId){
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";

        Object ret = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(requestId));
        if (OK.equals(ret)){
            return true;
        }
        return false;
    }
}
