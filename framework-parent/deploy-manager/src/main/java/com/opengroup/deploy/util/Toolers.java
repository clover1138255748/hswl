/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy.util;

import java.util.HashMap;
import java.util.Map;

import com.opengroup.tools.string.StringUtil;

/**
 * 
 * @author UU
 * @version $Id: Toolers.java, v 0.1 2017年1月11日 下午5:47:36 UU Exp $
 */
public class Toolers {
    public static final String SPACE_SPLIT = " ";

    public static final String EQUAL_SPLIT = "=";

    public static Map<String, Object> getSplitValue(String str) {
        String[] values = StringUtil.split(str, SPACE_SPLIT);
        if (values != null && values.length > 0) {
            int len = values.length;
            if (len > 0) {
                Map<String, Object> attributes = new HashMap<String, Object>();
                for (int i = 0; i < len; i++) {
                    String[] kv = values[i].split(EQUAL_SPLIT);
                    if (kv.length != 2) {
                        System.err.println("分隔格式不正确！");
                        continue;
                    } else {
                        attributes.put(kv[0], kv[1]);
                    }
                }
                return attributes;
            }
        }
        return null;
    }
}
