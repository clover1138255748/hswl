/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.rule;

import java.math.BigDecimal;
import java.util.Date;

/**
 * С�ڵ��ڵĹ���
 * @author UU
 * @version $Id: LERule.java, v 0.1 2016��12��22�� ����4:37:37 UU Exp $
 */
public class LERule extends AbstractSimpleRule {

    /**
     * @param id
     */
    public LERule(String id) {
        super(id);
    }

    @Override
    protected boolean check(Object so, Object to) {
        if (so instanceof Date) {
            Date tdo = getValue4Date(to);
            return ((Date) so).getTime() <= tdo.getTime();
        } else {
            BigDecimal sd = getValue4Number(so);
            BigDecimal td = getValue4Number(to);
            return sd.compareTo(td) <= 0;
        }
    }

}
