package com.opengroup.framework.dao;

import java.util.List;

import com.opengroup.common.anno.DaoTag;
import com.opengroup.exception.ErrorCode;

@DaoTag
public interface ErrorCodeDAO {
    /**
     * 插入错误
     * 
     * @param errorCodeDO
     */
    void insert(ErrorCode errorCodeDO);

    /**
     * 把一个系统中的错误码定义全部导出
     * 
     * @param system
     * @return
     */
    List<ErrorCode> loadAll(String system);
}
