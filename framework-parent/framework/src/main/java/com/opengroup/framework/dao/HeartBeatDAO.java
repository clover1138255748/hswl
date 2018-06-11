/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.framework.dao;

import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jin
 * @version $Id: HeartBeatDAO.java, v 0.1 2015��12��9�� ����12:00:32 jin Exp $
 */
public interface HeartBeatDAO {
    @Select("select 1 from dual")
    int heartBeat();
}
