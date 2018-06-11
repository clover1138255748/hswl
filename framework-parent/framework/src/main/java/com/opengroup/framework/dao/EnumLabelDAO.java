/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.framework.dao;

import java.util.List;
import java.util.Map;

import com.opengroup.common.anno.DaoTag;
import com.opengroup.tools.enumTool.EnumLabelDO;

/**
 * 枚举与文本的对应
 * @author jin
 * @version $Id: EnumLabelDAO.java, v 0.1 2015年8月21日 下午5:42:40 jin Exp $
 */
@DaoTag
public interface EnumLabelDAO {
    /**
     * 从数据库中查出所有的数据
     * 
     * @return
     */
    List<EnumLabelDO> loadAll();

    /**
     * 插入
     * 
     * @param enumLabelDO
     */
    void insert(EnumLabelDO enumLabelDO);

    /**
     * 删除
     * @param 参数
     */
    void delete(Map<String, Object> params);
}
