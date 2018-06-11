package com.opengroup.tools.reflection;

import org.reflections.Reflections;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.Set;

/**
 * 反射风
 * @author 刘飞
 */
public class ReflectionUtils {
    /**
     * 在指定包下面 获取目标注解的class集合
     * @param annotationClass : 目标注解
     * @param packagePath: 包的路径
     * @return 被注解 的类的集合
     */
    public static Set<Class<?>> getTypesAnnotatedClass(String packagePath, Class annotationClass) {
        if (packagePath == null || annotationClass == null){
            return null;
        }
        //目标非注解抛出异常
        if (!annotationClass.isAnnotation()){
            throw new IllegalArgumentException("目标非注解类");
        }
        Reflections reflections = new Reflections(packagePath);
        return  reflections.getTypesAnnotatedWith(annotationClass);
    }
}
