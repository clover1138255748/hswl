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
 * @version $Id: SequenceUtil.java, v 0.1 2015��6��29�� ����4:17:49 jin Exp $
 */
public class SequenceUtil {
    /**
     * ���е�DAO
     */
    @Autowired
    private SequenceDAO                     sequenceDAO;
    /**
     * ������ݾݵ�����ģ��
     */
    @Autowired
    @Qualifier("newTransactionTemplate")
    private TransactionTemplate             transactionTemplate;
    /**
     * ���еĻ�����󡣵�ǰ����
     */
    private Map<String, SequenceCacheValue> seqValueMap = new HashMap<String, SequenceCacheValue>(
                                                            100);

    /**
     * ȡ���е���һ��ֵ����1000�����档
     * 
     * @param sequenceName
     * @return
     */
    public synchronized long nextValue(String sequenceName) {
        SequenceCacheValue sv = seqValueMap.get(sequenceName);
        if (sv == null) {
            sv = new SequenceCacheValue();
            SequenceDO seqDO = fetchSequence(sequenceName);
            //TODO ���sequenceNameָ�������в����ڣ����׳���ָ���쳣�������쳣ջ��Ϣ�в���ֱ�۵Ŀ�����ʲôԭ���µģ����Խ���˴��ж�seqDO�Ƿ���null��Ȼ���׳�BizException
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
        //�ڶ�����������Ϊ�п�����������������дﵽ���ֵ�󣬻����ص� mod(��ǰֵ+incr*1000),��Ҫ����һ�������ָ�
        if (nextVal == -1) {
            sv.nextVal = fetchNextValFormDB(sequenceName);
            sv.currentVal = sv.nextVal - 999;
            nextVal = sv.currentVal;
        }
        return nextVal;

    }

    /**
     * ����������ȡ��һ��ֵ
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
     * ȡ������ֵ
     * 
     * @param sequenceName
     * @return
     */
    private SequenceDO fetchSequence(String sequenceName) {
        return sequenceDAO.selectSequence(sequenceName);
    }

    /**
     * ���е�ֵ������Ҫ�ǰ����еĻ������
     * 
     * @author jin
     * @version $Id: SequenceUtil.java, v 0.1 2015��6��29�� ����4:48:57 jin Exp $
     */
    private class SequenceCacheValue {
        /**
         * ��ǰ��ֵ
         */
        private long currentVal;
        /**
         * ��һ�η�����Ǩʱ��ֵ
         */
        private long nextVal;
        /**
         * ÿ����Ծ���ӵ�ֵ
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
