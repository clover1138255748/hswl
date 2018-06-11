/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.pojo.unitName;

import junit.framework.Assert;

import org.junit.Test;

import com.opengroup.common.unit.BaseUnitEntity;
import com.opengroup.common.unit.WeightUnitEnum;

/**
 * 
 * @author jin
 * @version $Id: WeightTest.java, v 0.1 2015年10月13日 下午7:46:30 jin Exp $
 */
public class WeightTest {
    @Test
    public void testSub() {
        BaseUnitEntity entity = new BaseUnitEntity(2D, WeightUnitEnum.T);
        BaseUnitEntity entitySub = new BaseUnitEntity(1100D, WeightUnitEnum.KG);
        entity.sub(entitySub);
        Assert.assertEquals(0.9D, entity.getValue());
    }

    @Test
    public void testAdd() {
        BaseUnitEntity entity = new BaseUnitEntity(2D, WeightUnitEnum.KG);
        BaseUnitEntity entityAdd = new BaseUnitEntity(1100D, WeightUnitEnum.G);
        entity.add(entityAdd);
        Assert.assertEquals(3.1D, entity.getValue());
    }

    @Test
    public void testExchange() {
        BaseUnitEntity entity = new BaseUnitEntity(2D, WeightUnitEnum.T);
        entity.exchange(WeightUnitEnum.G);
        Assert.assertEquals(2000000D, entity.getValue());
    }
}
