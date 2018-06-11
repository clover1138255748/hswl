/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.pojo.unitName;

import junit.framework.Assert;

import org.junit.Test;

import com.opengroup.common.unit.BaseUnitEntity;
import com.opengroup.common.unit.UnnormalizedUnitEnum;

/**
 * 
 * @author jin
 * @version $Id: UnnormalTest.java, v 0.1 2015年10月13日 下午7:50:45 jin Exp $
 */
public class UnnormalTest {

    @Test
    public void testSubDifferent() {
        try {
            BaseUnitEntity entity = new BaseUnitEntity(2D, UnnormalizedUnitEnum.TRUCK);
            BaseUnitEntity entitySub = new BaseUnitEntity(110D, UnnormalizedUnitEnum.BAG);
            entity.sub(entitySub);
        } catch (Exception e) {
            Assert.assertEquals("UnnormalizedUnitEnum NOT SUPPORT RATE", e.getMessage());
        }
    }

    @Test
    public void testSubSame() {
        BaseUnitEntity entity = new BaseUnitEntity(2D, UnnormalizedUnitEnum.TRUCK);
        BaseUnitEntity entitySub = new BaseUnitEntity(1D, UnnormalizedUnitEnum.TRUCK);
        entity.sub(entitySub);
        Assert.assertEquals(1D, entity.getValue());
    }

    @Test
    public void testAddDifferent() {
        try {
            BaseUnitEntity entity = new BaseUnitEntity(2D, UnnormalizedUnitEnum.TRUCK);
            BaseUnitEntity entitySub = new BaseUnitEntity(110D, UnnormalizedUnitEnum.BAG);
            entity.add(entitySub);
        } catch (Exception e) {
            Assert.assertEquals("UnnormalizedUnitEnum NOT SUPPORT RATE", e.getMessage());
        }
    }

    @Test
    public void testAddSame() {
        BaseUnitEntity entity = new BaseUnitEntity(2D, UnnormalizedUnitEnum.TRUCK);
        BaseUnitEntity entitySub = new BaseUnitEntity(1D, UnnormalizedUnitEnum.TRUCK);
        entity.add(entitySub);
        Assert.assertEquals(3D, entity.getValue());
    }

    @Test
    public void testExchange() {
        try {
            BaseUnitEntity entity = new BaseUnitEntity(2D, UnnormalizedUnitEnum.TRUCK);
            entity.exchange(UnnormalizedUnitEnum.TRUCK);
            Assert.assertEquals(2000D, entity.getValue());
        } catch (Exception e) {
            Assert.assertEquals("UnnormalizedUnitEnum NOT SUPPORT RATE", e.getMessage());
        }
    }
}
