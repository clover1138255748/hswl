package com.opengroup.tool.sequence;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opengroup.BaseSpringTest;
import com.opengroup.tools.sequence.SequenceUtil;

/**
 * 测试用例样例
 * 
 * @author jin
 * @version $Id: SequenceTest.java, v 0.1 2015年6月23日 下午2:32:28 jin Exp $
 */
public class SequenceTest extends BaseSpringTest {

    @Autowired
    private SequenceUtil sequenceUtil;

    /**
     * 多线程环境上的序列测试
     */
    @Test
    public void testMtSequence() {
        final int threadNum = 10;
        final int eachRun = 1100;
        final Long[] seqArray = new Long[threadNum * eachRun * 2];
        for (int i = 0; i < threadNum; i++) {
            final Integer ii = i;
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < eachRun; j++) {
                        Long seq = sequenceUtil.nextValue("test");
                        seqArray[ii * 1100 + j] = seq;
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error("", e);
        }
        Set<Long> set = new HashSet<Long>();
        for (Long l : seqArray) {
            if (l != null) {
                set.add(l);
            }
        }
        Assert.assertEquals(set.size(), threadNum * eachRun);
    }

    /**
     * 测试序列
     */
    @Test
    public void testSequence() {
        Long seq1 = sequenceUtil.nextValue("test");
        Long seq2 = sequenceUtil.nextValue("test");
        Assert.assertEquals(seq2 - seq1, 1);
    }

    @Test
    public void dailyTest() {
        long seq1 = sequenceUtil.nextValue("test_daily");
        long seq2 = sequenceUtil.nextValue("test_daily");
        Assert.assertEquals(seq2 - seq1, 1);
    }
}
