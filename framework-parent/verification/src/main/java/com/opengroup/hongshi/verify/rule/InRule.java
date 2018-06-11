/**
   * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.rule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.opengroup.hongshi.verify.Constant;
import com.opengroup.hongshi.verify.VerificationConfigException;

/**
 * in�Ĺ���
 * @author UU
 * @version $Id: InRule.java, v 0.1 2016��12��22�� ����4:38:33 UU Exp $
 */
public class InRule extends AbstractSimpleRule {

    /**
     * @param id
     */
    public InRule(String id) {
        super(id);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected boolean check(Object so, Object to) {
        Object[] tto = null;
        if (to instanceof String) {
            tto = to.toString().split(String.valueOf(Constant.SIGN_DATA_SPLIT));
        } else if (to instanceof Collection) {
            Collection cos = (Collection) to;
            int size = cos.size();
            tto = new Object[size];
            Iterator is = cos.iterator();
            int i = 0;
            while (is.hasNext()) {
                tto[i] = is.next();
                i++;
            }
        } else if (to.getClass().isArray()) {
            tto = (Object[]) to;
        } else {
            throw new VerificationConfigException("in��ƥ��Ŀ�겻��ת���������");
        }

        int count = tto.length;
        boolean result = false;
        if (so instanceof Date) {
            for (int i = 0; i < count; i++) {
                if (tto[i] instanceof Date) {
                    if (((Date) so).getTime() == ((Date) tto[i]).getTime()) {
                        result = true;
                        break;
                    }
                } else if (tto[i] instanceof String) {
                    Date dto = null;
                    try {
                        dto = this.getDateFormat().parse(to.toString());
                    } catch (ParseException e) {
                        throw new VerificationConfigException(
                            "in����ƥ���У����ܽ�ֵ[" + to + "]��[" + this.getExtras().get(EXTRA_DATEFORMAT)
                                                              + "]��ʽ��������ת��");
                    }
                    if (((Date) so).getTime() == dto.getTime()) {
                        result = true;
                        break;
                    }
                } else {
                    throw new VerificationConfigException(
                        "in��֧�ֱȽ�����������[" + tto[i].getClass().getName() + "]���ͽ��бȽ�");
                }
            }
        } else {
            Class<?> cls = so.getClass();
            Converter c = ConvertUtils.lookup(cls);
            if (c != null) {
                Object dso = c.convert(cls, so);
                if (dso instanceof Number) {
                    BigDecimal bd1 = this.getValue4Number(dso);
                    for (int i = 0; i < count; i++) {
                        BigDecimal bd2 = this.getValue4Number(tto[i]);
                        if (bd1.compareTo(bd2) == 0) {
                            result = true;
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < count; i++) {
                        Object dto = c.convert(cls, tto[i]);
                        if (dso.equals(dto)) {
                            result = true;
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < count; i++) {
                    if (so.equals(tto[i])) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

}
