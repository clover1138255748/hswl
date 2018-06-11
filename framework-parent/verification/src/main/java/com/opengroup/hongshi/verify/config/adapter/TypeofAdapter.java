/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.config.adapter;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;

import com.opengroup.hongshi.verify.MateData;
import com.opengroup.hongshi.verify.Rule;
import com.opengroup.hongshi.verify.VerificationConfigException;
import com.opengroup.hongshi.verify.config.jaxb.BaseRuleType;
import com.opengroup.hongshi.verify.config.jaxb.Extras;
import com.opengroup.hongshi.verify.rule.TypeofRule;

/**
 * 
 * @author UU
 * @version $Id: TypeofAdapter.java, v 0.1 2017年1月4日 下午2:53:36 UU Exp $
 */
public class TypeofAdapter extends AbstractAdapter {

    /** 
     * @see com.opengroup.hongshi.verify.config.AdapterAware#getAdapterName()
     */
    @Override
    public String getAdapterName() {
        return "typeof";
    }

    /** 
     * @see com.opengroup.hongshi.verify.config.Adapter#adapted(java.lang.Object, java.util.List, com.opengroup.hongshi.verify.config.jaxb.Extras, java.util.Map)
     */
    @Override
    public Rule adapted(BaseRuleType t, List<BaseRuleType> refRules, Extras extras,
                        Map<String, Object> parentExtras) {
        MateData so = getSourceMateData(t.getSource());
        MateData to = getTargetMateData(t.getTarget());

        switch (to.getType()) {
            case normal:
                Object o = to.getValue();
                try {
                    to.setValue(ClassUtils.getClass(String.valueOf(o)));
                } catch (ClassNotFoundException e) {
                    throw new VerificationConfigException("没有找到[" + o + "]类");
                }
            default:
                break;
        }
        TypeofRule result = new TypeofRule(getId(t));
        setRuleValue(result, t, extras, parentExtras, so, to);
        return result;
    }

}
