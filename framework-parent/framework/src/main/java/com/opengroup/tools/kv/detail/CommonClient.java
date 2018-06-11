/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.kv.detail;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 实际KV类DB的操作接口
 * @author jin
 * @version $Id: RedisClient.java, v 0.1 2015年8月12日 下午4:32:51 jin Exp $
 */
public interface CommonClient {
    /**
     * 保存
     * 
     * @param key
     * @param value
     * @param expiredSeconds
     */
    <V> void save(String key, V value, int expiredSeconds);

    /**
     * 读取
     * 
     * @param key
     * @return
     */
    <V> V read(String key, Class<V> c);

    /**
     * 删除
     * 
     * @param key
     */
    void delete(String key);

    /**
     * 分布式锁，如果超时指定时间还未取得锁，那么抛出异常
     * 
     * @param key
     * @return
     */
    <T> T lock(String key, DistributeLockProcessor<T> processor, int waitTimeMillis)
                                                                                    throws Exception;

    /**
     * 把一个数组插入到list中
     * 
     * @param key
     * @param list
     */
    <T> void add2List(String key, T... list);

    /**
     * 把一个数组插入到set中
     * 
     * @param key
     * @param list
     */
    <T> void add2Set(String key, T... set);

    /**
     * 读取一个列表并删除
     * 
     * @param key
     * @param c
     */
    <V> List<V> readAndDelList(String key, Class<V> c);

    /**
     * 读取一个列表
     * 
     * @param key
     * @param c
     */
    <V> List<V> readList(String key, Class<V> c);

    /**
     * 读取一个set
     * 
     * @param key
     * @param c
     * @return
     */
    <V> Set<V> readSet(String key, Class<V> c);

    /**
     * 读取一个set并删除
     * 
     * @param key
     * @param c
     */
    <V> Set<V> readAndDelSet(String key, Class<V> c);

    /**
     * 删除set的一个元素
     * 
     * @param key
     * @param vals
     */
    <V> void delSetItems(String key, V... vals);

    /**
     * 对key模糊查询
     * 
     * @param key
     */
    Set<String> like(String key);

    /**
     * 获取键到期的剩余时间(秒)
     * 
     * @param key
     */
    Long readLeftTime(String key);

    /**
     * 获取所有指定键的值。对于未持有一个字符串值，或者每一个键不存在，返回特殊值为nil。
     * 
     * @param keys
     */
    List<String> readAllKeyVal(String[] keys);

    long incrementAndGet(String key);

    void expire(String key, int expiredSeconds);
    
    
    /**
     * 保存map
     * @param key
     * @param map
     * @param expiredSeconds
     * @return
     */
    String save(String key, Map<String, String> map,
			int expiredSeconds) ;

    /** 
     * 删除前缀为{参数}的所有key<br> 
     * @param prefix 
     */  
     void deleteKeyByPrefix(String prefix);
    
    
    /** 
     * 删除key，可以是一个，也可以是多个key 
     * @param keys 
     */  
     void deleteKey(String ... keys );
     
     /**
      * 查询一个map
     * @param key
     * @return
     */
    public Map<String, String> readMap(String key);

    /**
     * 订阅一个键的值
     *
     */
    void subscribe(JedisPubSub jedisPubSub , String channel);


    /**
     * 订阅多个键的值
     *
     */
    void subscribe(JedisPubSub jedisPubSub , String... channels);

    /**
     *
     * @param channel: 发布消息的通道
     * @param message ：发布的消息
     */
    void publish(String channel , String message);


}
