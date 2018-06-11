/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.rule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.opengroup.hongshi.verify.VerificationConfigException;

/**
 * 相等的规则
 * @author UU
 * @version $Id: EqualsHanlder.java, v 0.1 2016年12月22日 下午12:01:16 UU Exp $
 */
public class EQRule extends AbstractSimpleRule {

    /**
     * @param id
     */
    public EQRule(String id) {
        super(id);
    }

    @Override
    protected boolean check(Object so, Object to) {
        if (so instanceof Date) {
            if (to instanceof Date) {
                return ((Date) so).getTime() == ((Date) to).getTime();
            } else if (to instanceof String) {
                Date dto = null;
                try {
                    dto = this.getDateFormat().parse(to.toString());
                } catch (ParseException e) {
                    throw new VerificationConfigException(
                        "不能将值[" + to + "]按[" + this.getExtras().get(EXTRA_DATEFORMAT)
                                                          + "]格式进行日期转换");
                }
                return ((Date) so).getTime() == dto.getTime();
            } else {
                throw new VerificationConfigException(
                    "不支持比较的数据类型[" + to.getClass().getName() + "]");
            }
        } else {
            Class<?> cls = so.getClass();
            Converter c = ConvertUtils.lookup(cls);
            if (c != null) {
                Object dso = c.convert(cls, so);
                if (dso instanceof Number) {
                    BigDecimal bd1 = this.getValue4Number(dso);
                    BigDecimal bd2 = this.getValue4Number(to);
                    return bd1.compareTo(bd2) == 0;
                } else {
                    Object dto = c.convert(cls, to);
                    return dso.equals(dto);
                }
            } else {
                return so.equals(to);
            }
        }
    }

}
