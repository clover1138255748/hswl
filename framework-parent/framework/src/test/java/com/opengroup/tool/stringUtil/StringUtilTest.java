/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tool.stringUtil;

import java.util.Map;

import org.junit.Test;

import com.opengroup.tools.string.StringUtil;

/**
 * 
 * @author jin
 * @version $Id: StringUtilTest.java, v 0.1 2015年8月26日 下午4:39:20 jin Exp $
 */
public class StringUtilTest {
    @Test
    public void testReplacement() {
        String content = "你好${name}, 你的}验证码是 ${${}33";
        System.out.println(handleValue(content, null));
    }

    @Test
    public void testNormalChars() {
        String s = "阿夺ssdfdf3409834i=34☆veve";
        System.out.println(StringUtil.containsEmoji(s));
    }

    /**
     * 需要替代的属性，标识前缀
     */
    private static final String SUBSTITUT_PREFIX = "${";

    /**
     * 需要替代的属性，标识后缀
     */
    private static final String SUBSTITUT_SUFFIX = "}";

    private String handleValue(String value, Map data) {
        if (!StringUtil.isBlank(value)) {
            int idx = 0;
            while (true) {
                int sdx = value.indexOf(SUBSTITUT_PREFIX, idx);
                if (sdx != -1) {
                    idx = sdx;
                    int edx = value.indexOf(SUBSTITUT_SUFFIX, sdx + 1);
                    if (edx != -1) {
                        idx = edx;
                        String key = value.substring(sdx + SUBSTITUT_PREFIX.length(), edx);
                        String replacement = getReplaceValue(data, key);
                        if (replacement != null) {
                            value = value.replace(SUBSTITUT_PREFIX + key + SUBSTITUT_SUFFIX,
                                replacement);
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return value;
    }

    private String getReplaceValue(Map data, String key) {
        return null;
    }
}
