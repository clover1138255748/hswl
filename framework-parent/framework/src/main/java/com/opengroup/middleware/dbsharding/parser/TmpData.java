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
 * @version $Id: TmpData.java, v 0.1 2015��10��13�� ����9:31:37 jin Exp $
 */
public class TmpData {
    public static ThreadLocal<Integer>                       dsNumTl                = new ThreadLocal<Integer>();
    /**
     * ��ǰ����Դ������, keyΪ����
     */
    public static ThreadLocal<Map<String, ShardingTableDef>> currentDsDef           = new ThreadLocal<Map<String, ShardingTableDef>>();
    /**
     * ��Ҫ���ֿ�ֱ��������б�
     */
    public static ThreadLocal<List<NeedShardingTableEntity>> needShardingTables     = new ThreadLocal<List<NeedShardingTableEntity>>();
    /**
     * ��ǰ�ı�
     */
    public static ThreadLocal<NeedShardingTableEntity>       currentExecuteTableDef = new ThreadLocal<NeedShardingTableEntity>();
    /**
     * ����SQL������shardingString
     */
    public static ThreadLocal<String>                        shardingString         = new ThreadLocal<String>();

    /**
     * ��ʼ��
     */
    public static void init(Map<String, ShardingTableDef> dsDefMap, int dsNum) {
        currentDsDef.set(dsDefMap);
        needShardingTables.set(new ArrayList<NeedShardingTableEntity>());
        dsNumTl.set(dsNum);
    }

    /**
     * ������е��̱߳���
     */
    public static void cleanAll() {
        currentDsDef.remove();
        needShardingTables.remove();
        currentExecuteTableDef.remove();
        shardingString.remove();
        dsNumTl.remove();
    }
}
