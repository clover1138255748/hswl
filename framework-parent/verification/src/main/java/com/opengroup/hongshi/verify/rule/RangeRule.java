/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.rule;

import java.math.BigDecimal;
import java.util.Date;

import com.opengroup.hongshi.verify.Constant;
import com.opengroup.hongshi.verify.VerificationConfigException;

/**
 * 范围性的规则
 * @author UU
 * @version $Id: RangeRule.java, v 0.1 2016年12月22日 下午4:39:29 UU Exp $
 */
public class RangeRule extends AbstractSimpleRule {

    /**
     * @param id
     */
    public RangeRule(String id) {
        super(id);
    }

    @Override
    protected boolean check(Object so, Object to) {
        RangeRuleValue rro = null;
        if (to instanceof RangeRuleValue) {
            rro = (RangeRuleValue) to;
        } else {
            rro = new RangeRuleValue(to.toString());
        }

        if (so instanceof Date) {
            Date sod = (Date) so;
            Date sdo = getValue4Date(rro.getStartObject());
            Date edo = getValue4Date(rro.getEndObject());

            boolean result = true;
            switch (rro.getPrefix()) {
                case Constant.CLOSE_INTERVAL_PREFIX:
                    result &= sod.getTime() >= sdo.getTime();
                    break;
                case Constant.OPEN_INTERVAL_PREFIX:
                    result &= sod.getTime() > sdo.getTime();
                    break;
            }
            switch (rro.getSuffix()) {
                case Constant.CLOSE_INTERVAL_SUFFIX:
                    result &= sod.getTime() <= edo.getTime();
                    break;
                case Constant.OPEN_INTERVAL_SUFFIX:
                    result &= sod.getTime() < edo.getTime();
                    break;
            }
            return result;
        } else {
            BigDecimal sod = getValue4Number(so);
            BigDecimal sd = getValue4Number(rro.getStartObject());
            BigDecimal td = getValue4Number(rro.getEndObject());
            boolean result = true;
            switch (rro.getPrefix()) {
                case Constant.CLOSE_INTERVAL_PREFIX:
                    result &= (sod.compareTo(sd) >= 0);
                    break;
                case Constant.OPEN_INTERVAL_PREFIX:
                    result &= (sod.compareTo(sd) > 0);
                    break;
            }
            switch (rro.getSuffix()) {
                case Constant.CLOSE_INTERVAL_SUFFIX:
                    result &= (sod.compareTo(td) <= 0);
                    break;
                case Constant.OPEN_INTERVAL_SUFFIX:
                    result &= (sod.compareTo(td) < 0);
                    break;
            }
            return result;
        }
    }

    public static class RangeRuleValue {

        private char   prefix;

        private char   suffix;

        private Object startObject;

        private Object endObject;

        public RangeRuleValue(String v) {
            if (v.startsWith(String.valueOf(Constant.OPEN_INTERVAL_PREFIX))) {
                prefix = Constant.OPEN_INTERVAL_PREFIX;
            } else if (v.startsWith(String.valueOf(Constant.CLOSE_INTERVAL_PREFIX))) {
                prefix = Constant.CLOSE_INTERVAL_PREFIX;
            } else {
                throw new VerificationConfigException("range规则匹配的数据结果不正确，正确格式(|[a,b]|)");
            }
            if (v.endsWith(String.valueOf(Constant.OPEN_INTERVAL_SUFFIX))) {
                suffix = Constant.OPEN_INTERVAL_SUFFIX;
            } else if (v.endsWith(String.valueOf(Constant.CLOSE_INTERVAL_SUFFIX))) {
                suffix = Constant.CLOSE_INTERVAL_SUFFIX;
            } else {
                throw new VerificationConfigException("range规则匹配的数据结果不正确，正确格式(|[a,b]|)");
            }
            String tmp = v.substring(1, v.length() - 1);
            String[] vs = tmp.split(",");
            if (vs.length != 2) {
                throw new VerificationConfigException("range规则匹配的数据结果不正确，正确格式(|[a,b]|)");
            }
            startObject = vs[0];
            endObject = vs[1];
        }

        public RangeRuleValue() {

        }

        public char getPrefix() {
            return prefix;
        }

        public void setPrefix(char prefix) {
            this.prefix = prefix;
        }

        public char getSuffix() {
            return suffix;
        }

        public void setSuffix(char suffix) {
            this.suffix = suffix;
        }

        public Object getStartObject() {
            return startObject;
        }

        public void setStartObject(Object startObject) {
            this.startObject = startObject;
        }

        public Object getEndObject() {
            return endObject;
        }

        public void setEndObject(Object endObject) {
            this.endObject = endObject;
        }

    }

}
