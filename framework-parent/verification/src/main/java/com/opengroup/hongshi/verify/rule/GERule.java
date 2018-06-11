/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.rule;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 大于等于的规则
 * @author UU
 * @version $Id: GERule.java, v 0.1 2016年12月22日 下午4:38:00 UU Exp $
 */
public class GERule extends AbstractSimpleRule {

    /**
     * @param id
     */
    public GERule(String id) {
        super(id);
    }

    @Override
    protected boolean check(Object so, Object to) {
        if (so instanceof Date) {
            Date tdo = getValue4Date(to);
            return ((Date) so).getTime() >= tdo.getTime();
        } else {
            BigDecimal sd = getValue4Number(so);
            BigDecimal td = getValue4Number(to);
            return sd.compareTo(td) >= 0;
        }
    }

}
