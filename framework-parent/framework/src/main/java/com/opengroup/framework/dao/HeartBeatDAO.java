/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.framework.dao;

import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jin
 * @version $Id: HeartBeatDAO.java, v 0.1 2015年12月9日 下午12:00:32 jin Exp $
 */
public interface HeartBeatDAO {
    @Select("select 1 from dual")
    int heartBeat();
}
