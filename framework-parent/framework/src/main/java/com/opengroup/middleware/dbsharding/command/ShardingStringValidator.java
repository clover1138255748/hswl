/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding.command;

/**
 * 
 * @author jin
 * @version $Id: ShardingStringValidator.java, v 0.1 2015年10月14日 下午9:12:10 jin Exp $
 */
public class ShardingStringValidator {

    public static boolean isShardingString(String str) {
        if (str != null && str.length() >= 4) {
            String segment = str.substring(str.length() - 4);
            if (segment.startsWith("P")) {
                try {
                    return Integer.valueOf(segment.substring(1)) > 0;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }
}
