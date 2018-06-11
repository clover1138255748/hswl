/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.rule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.beanutils.ConvertUtils;

import com.opengroup.hongshi.verify.VerificationConfigException;

/**
 * 
 * @author UU
 * @version $Id: AbstractSimpleRule.java, v 0.1 2017年1月5日 下午3:59:32 UU Exp $
 */
public abstract class AbstractSimpleRule extends AbstractRule {

    /**
     * @param id
     */
    public AbstractSimpleRule(String id) {
        super(id);
    }

    /** 
     * @see com.opengroup.hongshi.verify.rule.AbstractRule#matches(java.lang.Object[])
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected boolean matches(Object[] data) {
        Object so = tooler.getExpressionResult(getSource().getType(), getSource().getValue(), data);
        Object ro = tooler.getExpressionResult(getTarget().getType(), getTarget().getValue(), data);
        if (ro == null) {
            throw new VerificationConfigException("比较目标为null");
        }
        boolean result = true;
        if (so != null) {
            if (so.getClass().isArray()) {
                Object[] os = (Object[]) so;
                int count = os.length;
                for (int i = 0; i < count && result; i++) {
                    result &= check(os[i], ro);
                }
            } else if (so instanceof Collection) {
                Collection cs = (Collection) so;
                Iterator is = cs.iterator();
                while (is.hasNext() && result) {
                    result &= check(is.next(), ro);
                }
            } else {
                result = check(so, ro);
            }
        } else {
            result = check(so, ro);
        }
        return result;
    }

    protected abstract boolean check(Object so, Object to);

    /**
     * 将对象数据转化成数字类型
     * 
     * @param o
     * @return
     */
    protected BigDecimal getValue4Number(Object o) {
        BigDecimal result = null;
        try {
            result = (BigDecimal) ConvertUtils.convert(o, BigDecimal.class);
        } catch (Exception e) {
            throw new VerificationConfigException(
                this.getClass().getSimpleName() + "规则不支持非数字类型[" + o + "]转化成数字进行比较");
        }
        return result;
    }

    /**
     * 将对象数据转化成日期类型
     * 
     * @param o
     * @return
     */
    protected Date getValue4Date(Object o) {
        if (o instanceof Date) {
            return (Date) o;
        } else if (o instanceof String) {
            try {
                return getDateFormat().parse(o.toString());
            } catch (ParseException e) {
                throw new VerificationConfigException(
                    "不能将值[" + o + "]按[" + this.getExtras().get(EXTRA_DATEFORMAT) + "]格式进行日期转换");
            }
        } else {
            throw new VerificationConfigException(
                "不支持比较的数据类型[" + o.getClass().getName() + "]转成成日期类型");
        }
    }
}
