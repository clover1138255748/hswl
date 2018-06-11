/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.sequence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.opengroup.common.sql.SqlParamFactory;
import com.opengroup.framework.dao.SequenceDAO;
import com.opengroup.tools.date.DateFormatterEnum;
import com.opengroup.tools.date.DateUtil;

/**
 * 
 * @author jin
 * @version $Id: SequenceUtil.java, v 0.1 2015年6月29日 下午4:17:49 jin Exp $
 */
public class SequenceUtil {
    /**
     * 序列的DAO
     */
    @Autowired
    private SequenceDAO                     sequenceDAO;
    /**
     * 框架数据据的事务模板
     */
    @Autowired
    @Qualifier("newTransactionTemplate")
    private TransactionTemplate             transactionTemplate;
    /**
     * 序列的缓存对象。当前可用
     */
    private Map<String, SequenceCacheValue> seqValueMap = new HashMap<String, SequenceCacheValue>(
                                                            100);

    /**
     * 取序列的下一个值，有1000个缓存。
     * 
     * @param sequenceName
     * @return
     */
    public synchronized long nextValue(String sequenceName) {
        SequenceCacheValue sv = seqValueMap.get(sequenceName);
        if (sv == null) {
            sv = new SequenceCacheValue();
            SequenceDO seqDO = fetchSequence(sequenceName);
            //TODO 如果sequenceName指定的序列不存在，会抛出空指针异常，但在异常栈信息中不能直观的看出是什么原因导致的，所以建议此处判断seqDO是否是null，然后抛出BizException
            int incr = seqDO.getIncrement();
            seqValueMap.put(sequenceName, sv);
            sv.currentVal = seqDO.getCurrentValue() + 1;
            sv.nextVal = fetchNextValFormDB(sequenceName);
            sv.incr = incr;
        }
        long nextVal = sv.nextValue();
        if (nextVal == -1) {
            sv.nextVal = fetchNextValFormDB(sequenceName);
            sv.currentVal = sv.nextVal - 999;
            nextVal = sv.currentVal;
        }
        //第二次做，是因为有跨界的情况发生，即序列达到最大值后，会跳回到 mod(当前值+incr*1000),需要再做一次以做恢复
        if (nextVal == -1) {
            sv.nextVal = fetchNextValFormDB(sequenceName);
            sv.currentVal = sv.nextVal - 999;
            nextVal = sv.currentVal;
        }
        return nextVal;

    }

    /**
     * 开启新事务，取下一个值
     * 
     * @param seqName
     * @return
     */
    private long fetchNextValFormDB(final String seqName) {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {
                String dateStr = DateUtil.format(new Date(),
                    DateFormatterEnum.SIMPLE_WITHOUT_SEP.getCode());
                Map<String, Object> params = SqlParamFactory.fetch();
                params.put("seqName", seqName);
                params.put("todayInt", Integer.parseInt(dateStr));
                try {
                    return sequenceDAO.nextValue(params);
                } finally {
                    SqlParamFactory.recycle(params);
                }
            }
        });
    }

    /**
     * 取增长的值
     * 
     * @param sequenceName
     * @return
     */
    private SequenceDO fetchSequence(String sequenceName) {
        return sequenceDAO.selectSequence(sequenceName);
    }

    /**
     * 序列的值对象，主要是把序列的缓存操作
     * 
     * @author jin
     * @version $Id: SequenceUtil.java, v 0.1 2015年6月29日 下午4:48:57 jin Exp $
     */
    private class SequenceCacheValue {
        /**
         * 当前的值
         */
        private long currentVal;
        /**
         * 下一次发生变迁时的值
         */
        private long nextVal;
        /**
         * 每次跳跃增加的值
         */
        private int  incr;

        public long nextValue() {
            if (currentVal + incr > nextVal) {
                return -1;
            }
            this.currentVal += incr;
            return this.currentVal;
        }
    }
}
