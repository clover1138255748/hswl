package com.opengroup.framework.dao;

import java.util.List;

import com.opengroup.common.anno.DaoTag;
import com.opengroup.exception.ErrorCode;

@DaoTag
public interface ErrorCodeDAO {
    /**
     * �������
     * 
     * @param errorCodeDO
     */
    void insert(ErrorCode errorCodeDO);

    /**
     * ��һ��ϵͳ�еĴ����붨��ȫ������
     * 
     * @param system
     * @return
     */
    List<ErrorCode> loadAll(String system);
}
