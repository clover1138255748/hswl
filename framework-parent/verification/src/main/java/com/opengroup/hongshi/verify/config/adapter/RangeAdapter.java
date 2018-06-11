/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.config.adapter;

import java.util.List;
import java.util.Map;

import com.opengroup.hongshi.verify.MateData;
import com.opengroup.hongshi.verify.Rule;
import com.opengroup.hongshi.verify.config.jaxb.BaseRuleType;
import com.opengroup.hongshi.verify.config.jaxb.Extras;
import com.opengroup.hongshi.verify.rule.RangeRule;

/**
 * 
 * @author UU
 * @version $Id: RangeAdapter.java, v 0.1 2017年1月4日 下午2:52:15 UU Exp $
 */
public class RangeAdapter extends AbstractAdapter {

    /** 
     * @see com.opengroup.hongshi.verify.config.AdapterAware#getAdapterName()
     */
    @Override
    public String getAdapterName() {
        return "range";
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
                to.setValue(new RangeRule.RangeRuleValue(to.getValue().toString()));
            default:
                break;
        }
        RangeRule result = new RangeRule(getId(t));
        setRuleValue(result, t, extras, parentExtras, so, to);
        return result;
    }

}
