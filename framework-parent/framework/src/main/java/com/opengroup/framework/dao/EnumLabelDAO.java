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
 * ö�����ı��Ķ�Ӧ
 * @author jin
 * @version $Id: EnumLabelDAO.java, v 0.1 2015��8��21�� ����5:42:40 jin Exp $
 */
@DaoTag
public interface EnumLabelDAO {
    /**
     * �����ݿ��в�����е�����
     * 
     * @return
     */
    List<EnumLabelDO> loadAll();

    /**
     * ����
     * 
     * @param enumLabelDO
     */
    void insert(EnumLabelDO enumLabelDO);

    /**
     * ɾ��
     * @param ����
     */
    void delete(Map<String, Object> params);
}
