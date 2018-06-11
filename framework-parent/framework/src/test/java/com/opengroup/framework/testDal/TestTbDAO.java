/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.framework.testDal;

import com.opengroup.common.anno.DaoTag;

/**
 * 
 * @author jin
 * @version $Id: TestTbDAO.java, v 0.1 2015年10月15日 下午8:21:38 jin Exp $
 */
@DaoTag
public interface TestTbDAO {

    void insert(TestTbDO testTbDO);

    void selectById(String id);

    void selectByName(String name);

    void delete(String id);

    void update(TestTbDO testTbDO);
}
