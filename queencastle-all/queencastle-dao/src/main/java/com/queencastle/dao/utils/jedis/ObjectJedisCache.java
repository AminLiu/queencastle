package com.queencastle.dao.utils.jedis;


import org.springframework.util.SerializationUtils;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ObjectJedisCache extends AbstractJedisCache {

    public ObjectJedisCache(String appName, ShardedJedisPool shardedJedisPool, String nameSpace) {
        super(appName, shardedJedisPool, nameSpace);
    }

    public ObjectJedisCache(String appName, ShardedJedisPool shardedJedisPool, int period,
            String nameSpace) {
        super(appName, shardedJedisPool, period, nameSpace);
    }

    public boolean setObj(String key, Object obj) {
        ShardedJedis jedis = null;
        try {
            jedis = super.getShardedJedis();
            byte[] bytes = SerializationUtils.serialize(obj);
            String newKey = appName + "_" + nameSpace + "_" + key;
            if (period > 0) {
                jedis.setex(newKey.getBytes(), period, bytes);
            } else {
                jedis.set(newKey.getBytes(), bytes);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            super.returnResource(jedis);
        }
        return false;
    }

    public Object getObj(String key) {
        Object value = null;
        ShardedJedis jedis = null;
        try {
            jedis = super.getShardedJedis();
            String newKey = appName + "_" + nameSpace + "_" + key;
            byte[] bytes = jedis.get(newKey.getBytes());
            value = SerializationUtils.deserialize(bytes);
            return value;
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            super.returnResource(jedis);
        }
        return value;
    }


    @Override
    public void clearKey(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = super.getShardedJedis();
            jedis.del(appName + "_" + nameSpace + "_" + key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            super.returnResource(jedis);
        }
    }

}
