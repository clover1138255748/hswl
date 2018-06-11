/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.config.adapter;

import java.util.List;
import java.util.Map;

import com.opengroup.hongshi.verify.ExpressionType;
import com.opengroup.hongshi.verify.MateData;
import com.opengroup.hongshi.verify.Rule;
import com.opengroup.hongshi.verify.config.jaxb.BaseRuleType;
import com.opengroup.hongshi.verify.config.jaxb.Extras;
import com.opengroup.hongshi.verify.rule.AndRule;

/**
 * 
 * @author UU
 * @version $Id: AndAdapter.java, v 0.1 2017年1月4日 下午2:23:34 UU Exp $
 */
public class AndAdapter extends AbstractMultipleAdapter {

    /** 
     * @see com.opengroup.hongshi.verify.config.AdapterAware#getAdapterName()
     */
    @Override
    public String getAdapterName() {
        return "and";
    }

    /** 
     * @see com.opengroup.hongshi.verify.config.Adapter#adapted(java.lang.Object, java.util.List, com.opengroup.hongshi.verify.config.jaxb.Extras, java.util.Map)
     */
    @Override
    public Rule adapted(BaseRuleType t, List<BaseRuleType> refRules, Extras extras,
                        Map<String, Object> parentExtras) {
        BaseRuleType st = this.getChildRuleType(refRules, t.getSource());
        BaseRuleType tt = this.getChildRuleType(refRules, t.getTarget());
        Map<String, Object> currentExtras = getExtras(extras, t.getExtra(), parentExtras);
        Rule sr = adapterFactory.getAdapter(st.getAdapterName()).adapted(st, refRules, extras,
            currentExtras);
        MateData sm = new MateData();
        sm.setType(ExpressionType.rule);
        sm.setValue(sr);
        Rule tr = adapterFactory.getAdapter(tt.getAdapterName()).adapted(tt, refRules, extras,
            currentExtras);
        MateData tm = new MateData();
        tm.setType(ExpressionType.rule);
        tm.setValue(tr);
        AndRule result = new AndRule(getId(t));
        result.setCode(t.getCode());
        result.setExtras(currentExtras);
        result.setTooler(tooler);
        result.setMessage(tooler.buildMateData(t.getMessage()));
        result.setNot(t.isNot());
        result.setSource(sm);
        result.setTarget(tm);
        return result;
    }

}
