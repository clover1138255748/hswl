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
 * ʵ��KV��DB�Ĳ����ӿ�
 * @author jin
 * @version $Id: RedisClient.java, v 0.1 2015��8��12�� ����4:32:51 jin Exp $
 */
public interface CommonClient {
    /**
     * ����
     * 
     * @param key
     * @param value
     * @param expiredSeconds
     */
    <V> void save(String key, V value, int expiredSeconds);

    /**
     * ��ȡ
     * 
     * @param key
     * @return
     */
    <V> V read(String key, Class<V> c);

    /**
     * ɾ��
     * 
     * @param key
     */
    void delete(String key);

    /**
     * �ֲ�ʽ���������ʱָ��ʱ�仹δȡ��������ô�׳��쳣
     * 
     * @param key
     * @return
     */
    <T> T lock(String key, DistributeLockProcessor<T> processor, int waitTimeMillis)
                                                                                    throws Exception;

    /**
     * ��һ��������뵽list��
     * 
     * @param key
     * @param list
     */
    <T> void add2List(String key, T... list);

    /**
     * ��һ��������뵽set��
     * 
     * @param key
     * @param list
     */
    <T> void add2Set(String key, T... set);

    /**
     * ��ȡһ���б�ɾ��
     * 
     * @param key
     * @param c
     */
    <V> List<V> readAndDelList(String key, Class<V> c);

    /**
     * ��ȡһ���б�
     * 
     * @param key
     * @param c
     */
    <V> List<V> readList(String key, Class<V> c);

    /**
     * ��ȡһ��set
     * 
     * @param key
     * @param c
     * @return
     */
    <V> Set<V> readSet(String key, Class<V> c);

    /**
     * ��ȡһ��set��ɾ��
     * 
     * @param key
     * @param c
     */
    <V> Set<V> readAndDelSet(String key, Class<V> c);

    /**
     * ɾ��set��һ��Ԫ��
     * 
     * @param key
     * @param vals
     */
    <V> void delSetItems(String key, V... vals);

    /**
     * ��keyģ����ѯ
     * 
     * @param key
     */
    Set<String> like(String key);

    /**
     * ��ȡ�����ڵ�ʣ��ʱ��(��)
     * 
     * @param key
     */
    Long readLeftTime(String key);

    /**
     * ��ȡ����ָ������ֵ������δ����һ���ַ���ֵ������ÿһ���������ڣ���������ֵΪnil��
     * 
     * @param keys
     */
    List<String> readAllKeyVal(String[] keys);

    long incrementAndGet(String key);

    void expire(String key, int expiredSeconds);
    
    
    /**
     * ����map
     * @param key
     * @param map
     * @param expiredSeconds
     * @return
     */
    String save(String key, Map<String, String> map,
			int expiredSeconds) ;

    /** 
     * ɾ��ǰ׺Ϊ{����}������key<br> 
     * @param prefix 
     */  
     void deleteKeyByPrefix(String prefix);
    
    
    /** 
     * ɾ��key��������һ����Ҳ�����Ƕ��key 
     * @param keys 
     */  
     void deleteKey(String ... keys );
     
     /**
      * ��ѯһ��map
     * @param key
     * @return
     */
    public Map<String, String> readMap(String key);

    /**
     * ����һ������ֵ
     *
     */
    void subscribe(JedisPubSub jedisPubSub , String channel);


    /**
     * ���Ķ������ֵ
     *
     */
    void subscribe(JedisPubSub jedisPubSub , String... channels);

    /**
     *
     * @param channel: ������Ϣ��ͨ��
     * @param message ����������Ϣ
     */
    void publish(String channel , String message);


}
