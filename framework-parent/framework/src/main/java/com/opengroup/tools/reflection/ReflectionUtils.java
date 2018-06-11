package com.opengroup.tools.reflection;

import org.reflections.Reflections;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.Set;

/**
 * �����
 * @author ����
 */
public class ReflectionUtils {
    /**
     * ��ָ�������� ��ȡĿ��ע���class����
     * @param annotationClass : Ŀ��ע��
     * @param packagePath: ����·��
     * @return ��ע�� ����ļ���
     */
    public static Set<Class<?>> getTypesAnnotatedClass(String packagePath, Class annotationClass) {
        if (packagePath == null || annotationClass == null){
            return null;
        }
        //Ŀ���ע���׳��쳣
        if (!annotationClass.isAnnotation()){
            throw new IllegalArgumentException("Ŀ���ע����");
        }
        Reflections reflections = new Reflections(packagePath);
        return  reflections.getTypesAnnotatedWith(annotationClass);
    }
}
