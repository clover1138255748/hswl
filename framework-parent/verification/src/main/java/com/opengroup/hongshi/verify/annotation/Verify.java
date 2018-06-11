/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author UU
 * @version $Id: Verify.java, v 0.1 2016年12月26日 下午12:59:59 UU Exp $
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Verify {
    /**
     * 验证器名称，唯一标识
     * 
     * @return
     */
    String name() default "";
}
