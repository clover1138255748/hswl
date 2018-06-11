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
import com.opengroup.hongshi.verify.rule.EQRule;

/**
 * 
 * @author UU
 * @version $Id: EqAdapter.java, v 0.1 2017年1月4日 下午2:32:27 UU Exp $
 */
public class EqAdapter extends AbstractAdapter {

    /** 
     * @see com.opengroup.hongshi.verify.config.AdapterAware#getAdapterName()
     */
    @Override
    public String getAdapterName() {
        return "eq";
    }

    /** 
     * @see com.opengroup.hongshi.verify.config.Adapter#adapted(java.lang.Object, java.util.List, com.opengroup.hongshi.verify.config.jaxb.Extras, java.util.Map)
     */
    @Override
    public Rule adapted(BaseRuleType t, List<BaseRuleType> refRules, Extras extras,
                        Map<String, Object> parentExtras) {
        MateData so = getSourceMateData(t.getSource());
        MateData to = getTargetMateData(t.getTarget());

        EQRule result = new EQRule(getId(t));
        setRuleValue(result, t, extras, parentExtras, so, to);
        return result;
    }

}
