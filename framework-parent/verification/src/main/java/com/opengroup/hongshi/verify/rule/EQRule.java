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
 * ��ȵĹ���
 * @author UU
 * @version $Id: EqualsHanlder.java, v 0.1 2016��12��22�� ����12:01:16 UU Exp $
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
                        "���ܽ�ֵ[" + to + "]��[" + this.getExtras().get(EXTRA_DATEFORMAT)
                                                          + "]��ʽ��������ת��");
                }
                return ((Date) so).getTime() == dto.getTime();
            } else {
                throw new VerificationConfigException(
                    "��֧�ֱȽϵ���������[" + to.getClass().getName() + "]");
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
