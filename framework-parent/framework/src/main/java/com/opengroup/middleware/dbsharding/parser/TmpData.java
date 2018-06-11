/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opengroup.middleware.dbsharding.ShardingTableDef;

/**
 * 
 * @author jin
 * @version $Id: TmpData.java, v 0.1 2015年10月13日 下午9:31:37 jin Exp $
 */
public class TmpData {
    public static ThreadLocal<Integer>                       dsNumTl                = new ThreadLocal<Integer>();
    /**
     * 当前数据源的配置, key为表名
     */
    public static ThreadLocal<Map<String, ShardingTableDef>> currentDsDef           = new ThreadLocal<Map<String, ShardingTableDef>>();
    /**
     * 需要做分库分表变更的所有表
     */
    public static ThreadLocal<List<NeedShardingTableEntity>> needShardingTables     = new ThreadLocal<List<NeedShardingTableEntity>>();
    /**
     * 当前的表
     */
    public static ThreadLocal<NeedShardingTableEntity>       currentExecuteTableDef = new ThreadLocal<NeedShardingTableEntity>();
    /**
     * 本次SQL操作的shardingString
     */
    public static ThreadLocal<String>                        shardingString         = new ThreadLocal<String>();

    /**
     * 初始化
     */
    public static void init(Map<String, ShardingTableDef> dsDefMap, int dsNum) {
        currentDsDef.set(dsDefMap);
        needShardingTables.set(new ArrayList<NeedShardingTableEntity>());
        dsNumTl.set(dsNum);
    }

    /**
     * 清除所有的线程变量
     */
    public static void cleanAll() {
        currentDsDef.remove();
        needShardingTables.remove();
        currentExecuteTableDef.remove();
        shardingString.remove();
        dsNumTl.remove();
    }
}
