/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.kv.detail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import com.alibaba.fastjson.JSON;
import com.opengroup.exception.WaitTimeoutException;
import com.opengroup.tools.string.StringUtil;

/**
 * Redis的实现类
 * @author jin
 * @version $Id: RedisClientImpl.java, v 0.1 2015年8月12日 下午4:36:43 jin Exp $
 */
public class RedisClientImpl implements CommonClient {

    @Autowired
    private RedisConnPool redisConnPool;

    /**
     *  @see com.opengroup.tools.kv.detail.CommonClient#save(java.lang.String, java.lang.Object, long)
     * @param key
     * @param value
     * @param expiredSeconds
     */
    @Override
    public <V> void save(String key, V value, int expiredSeconds) {
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            jedis.set(key, JSON.toJSONString(value));
            if (expiredSeconds != 0)
                jedis.expire(key, expiredSeconds);

        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
    }

    /** 
     * @see com.opengroup.tools.kv.detail.CommonClient#read(java.lang.String)
     */
    @Override
    public <V> V read(String key, Class<V> c) {
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            String value = jedis.get(key);
            if (!StringUtil.isBlank(value)) {
                return JSON.parseObject(value, c);
            }
            return null;
        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
    }

    /** 
     * @see com.opengroup.tools.kv.detail.CommonClient#delete(java.lang.String)
     */
    @Override
    public void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            jedis.del(key);
        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
    }

    /** 
     * @see com.opengroup.tools.kv.detail.CommonClient#lock(java.lang.String)
     */
    @Override
    public <T> T lock(String key, DistributeLockProcessor<T> processor, int waitTimeMillis)
                                                                                           throws Exception {
        Jedis jedis = redisConnPool.getConnection();
        long beatHeartTime = 1000;
        long sleepTime = 0;
        long leftTime = waitTimeMillis;
        boolean locked = false;
        try {
            for (int i = 0; i <= waitTimeMillis; i += sleepTime) {
                long result = jedis.setnx(key, "");
                if (result == 1) {
                    locked = true;
                    return processor.process();
                } else {
                    leftTime = waitTimeMillis - i;
                    if (leftTime > beatHeartTime) {
                        sleepTime = beatHeartTime;
                    } else {
                        sleepTime = leftTime;
                    }
                    if (sleepTime <= 0)
                        break;
                    Thread.sleep(sleepTime);
                }
            }
            throw new WaitTimeoutException("等待分布式锁超时[" + waitTimeMillis + "]");
        } finally {
            try {
                if (locked) {
                    jedis.del(key);
                }
            } finally {
                redisConnPool.recycle(jedis);
            }
        }
    }

    /**
     * 
     * @see com.opengroup.tools.kv.detail.CommonClient#add2List(java.lang.String, java.lang.Object[])
     */
    @Override
    public <T> void add2List(String key, T... list) {
        if (list != null && list.length > 0) {
            Jedis jedis = redisConnPool.getConnection();
            try {
                String[] listString = new String[list.length];
                for (int i = 0; i < list.length; i++) {
                    T t = list[i];
                    if (t instanceof String) {
                        listString[i] = (String) t;
                    } else {
                        listString[i] = JSON.toJSONString(t);
                    }
                }
                jedis.rpush(key, listString);
            } finally {
                redisConnPool.recycle(jedis);
            }
        }
    }

    /**
     * 
     * @see com.opengroup.tools.kv.detail.CommonClient#readAndDelList(java.lang.String, java.lang.Class)
     */
    @Override
    public <V> List<V> readAndDelList(String key, Class<V> c) {
        Jedis jedis = redisConnPool.getConnection();
        try {
            List<String> list = jedis.lrange(key, 0, -1);
            if (list != null && list.size() > 0) {
                List<V> result = new ArrayList<V>();
                for (String str : list) {
                    V value = JSON.parseObject(str, c);
                    result.add(value);
                }
                return result;
            }
        } finally {
            try {
                jedis.del(key);
            } finally {
                redisConnPool.recycle(jedis);
            }
        }
        return null;
    }

    /** 
     * @see com.opengroup.tools.kv.detail.CommonClient#readList(java.lang.String, java.lang.Class)
     */
    @Override
    public <V> List<V> readList(String key, Class<V> c) {
        Jedis jedis = redisConnPool.getConnection();
        try {
            List<String> list = jedis.lrange(key, 0, -1);

            if (list != null && list.size() > 0) {
                List<V> result = new ArrayList<V>();
                for (String str : list) {
                    if (c == String.class) {
                        result.add(c.cast(str));
                    } else {
                        V value = JSON.parseObject(str, c);
                        result.add(value);
                    }
                }
                return result;
            }
        } finally {
            redisConnPool.recycle(jedis);
        }
        return null;
    }

    /** 
     * @see com.opengroup.tools.kv.detail.CommonClient#add2Set(java.lang.String, java.lang.Object[])
     */
    @Override
    public <T> void add2Set(String key, T... set) {
        if (set == null || set.length == 0)
            return;
        Jedis jedis = redisConnPool.getConnection();
        try {
            for (T t : set) {
                if (t instanceof String) {
                    jedis.sadd(key, (String) t);
                } else {
                    jedis.sadd(key, JSON.toJSONString(t));
                }
            }
        } finally {
            redisConnPool.recycle(jedis);
        }
    }

    /** 
     * @see com.opengroup.tools.kv.detail.CommonClient#readSet(java.lang.String, java.lang.Class)
     */
    @Override
    public <V> Set<V> readSet(String key, Class<V> c) {
        Jedis jedis = redisConnPool.getConnection();
        try {
            Set<String> values = jedis.smembers(key);
            if (values != null && values.size() > 0) {
                Set<V> result = new HashSet<V>();
                for (String s : values) {
                    if (c == String.class) {
                        result.add(c.cast(s));
                    } else {
                        result.add(JSON.parseObject(s, c));
                    }
                }
                return result;
            }
            return null;
        } finally {
            redisConnPool.recycle(jedis);
        }
    }

    /** 
     * @see com.opengroup.tools.kv.detail.CommonClient#readAndDelSet(java.lang.String, java.lang.Class)
     */
    @Override
    public <V> Set<V> readAndDelSet(String key, Class<V> c) {
        Jedis jedis = redisConnPool.getConnection();
        try {
            Set<String> values = jedis.smembers(key);
            if (values != null && values.size() > 0) {
                Set<V> result = new HashSet<V>();
                for (String s : values) {
                    result.add(JSON.parseObject(s, c));
                }
                return result;
            }
            return null;
        } finally {
            try {
                jedis.del(key);
            } finally {
                redisConnPool.recycle(jedis);
            }
        }
    }

    /**
     * 
     * @see com.opengroup.tools.kv.detail.CommonClient#delSetItems(java.lang.String, java.lang.Object[])
     */
    @Override
    public <V> void delSetItems(String key, V... vals) {
        Jedis jedis = redisConnPool.getConnection();
        try {
            String[] members = new String[vals.length];
            for (int i = 0; i < vals.length; i++) {
                members[i] = JSON.toJSONString(vals[i]);
            }
            jedis.srem(key, members);
        } finally {
            redisConnPool.recycle(jedis);
        }
    }

    /** 
     * 对key的模糊查询
     * @param key 
     * @return 
     */
    @Override
    public Set<String> like(String key) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = redisConnPool.getConnection();
            res = jedis.keys(key);
        } finally {
            redisConnPool.recycle(jedis);
        }
        return res;
    }

    /** 
     * 获取键到期的剩余时间(秒)
     * @param key 
     * @return 
     */
    @Override
    public Long readLeftTime(String key) {
        Jedis jedis = null;
        Long res = 0l;
        try {
            jedis = redisConnPool.getConnection();
            res = jedis.ttl(key);
        } finally {
            redisConnPool.recycle(jedis);
        }
        return res;
    }

    /** 
     * 获取所有指定键的值。对于未持有一个字符串值，或者每一个键不存在，返回特殊值为nil。
     * @param keys 
     * @return 
     */
    @Override
    public List<String> readAllKeyVal(String[] keys) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = redisConnPool.getConnection();
            res = jedis.mget(keys);
        } finally {
            redisConnPool.recycle(jedis);
        }
        return res;
    }

    @Override
    public long incrementAndGet(String key) {
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            return jedis.incr(key);
        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
    }

    @Override
    public void expire(String key, int expiredSeconds) {
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            jedis.expire(key, expiredSeconds);
        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
    }

    @Override
    public String save(String key, Map<String, String> map, int expiredSeconds) {
        String res = null;
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            res = jedis.hmset(key, map);
            if (expiredSeconds != 0)
                jedis.expire(key, expiredSeconds);
        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
        return res;
    }

    @Override
    public Map<String, String> readMap(String key) {
        Map<String, String> hgetAll = null;
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            hgetAll = jedis.hgetAll(key);

        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
        return hgetAll;
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        if (jedisPubSub == null || channels.length < 1) {
            throw new IllegalArgumentException("订阅消息时传入参数错误");
        }
        Jedis jedis = null;
        try {

            jedis = redisConnPool.getConnection();
            jedis.subscribe(jedisPubSub, channels);
        } finally {
            if (jedis != null) {
                redisConnPool.recycle(jedis);
            }
        }

    }

    @Override
    public void publish(String channel, String message) {
        if (StringUtil.isBlank(channel)) {
            throw new IllegalArgumentException("发布消息时传入参数错误");
        }
        Jedis jedis = null;
        try {

            jedis = redisConnPool.getConnection();
            jedis.publish(channel, message);
        } finally {
            if (jedis != null) {
                redisConnPool.recycle(jedis);
            }
        }

    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String channel) {

        if (jedisPubSub == null || StringUtil.isBlank(channel)) {
            throw new IllegalArgumentException("订阅消息时传入参数错误");
        }
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            jedis.subscribe(jedisPubSub, channel);
        } finally {
            if (jedis != null) {
                redisConnPool.recycle(jedis);
            }
        }

    }

    /**
     * 删除匹配的key<br> 
     * 如以my为前缀的则 参数为"my*" 
     * @param key 
     */
    public void deleteKeys(String pattern) {
        //列出所有匹配的key  
    	Jedis jedis = redisConnPool.getConnection();
        Set<String> keySet = jedis.keys(pattern);  
        try {
            if(!CollectionUtils.isEmpty(keySet)){  
                String keyArr[] = new String[keySet.size()];  
                int i =0;  
                for (String keys : keySet) {  
                    keyArr[i] = keys;  
                    i++;
                }  
                jedis.del(keyArr);
            }  
        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
    }  

    /** 
     * 删除前缀为{参数}的所有key<br> 
     * @param prefix 
     */
    @Override
    public void deleteKeyByPrefix(String prefix) {
        deleteKeys(prefix + "*");
    }

    /** 
     * 删除key，可以是一个，也可以是多个key 
     * @param keys 
     */
    @Override
    public void deleteKey(String... keys) {
        Jedis jedis = null;
        try {
            jedis = redisConnPool.getConnection();
            jedis.del(keys);
        } finally {
            if (jedis != null)
                redisConnPool.recycle(jedis);
        }
    }

}
