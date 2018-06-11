package com.opengroup.tools.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.opengroup.exception.BaseFrameworkException;

/**
 * �������������
 * 
 * @author ruimin.jrm
 * @version $Id: BeanUtil.java, v 0.1 2015��6��5�� ����6:02:46 ruimin.jrm Exp $
 */
public class BeanUtil {

    /**
     * ��beanת���� byte[]
     * 
     * @param obj
     * @return
     */
    public static byte[] convertToBytes(Serializable obj) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            baos.flush();

            byte[] content = baos.toByteArray();
            return content;
        } catch (Throwable e) {
            throw new BaseFrameworkException(e, "���ƶ������");
        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (oos != null)
                    oos.close();
            } catch (Throwable e) {
                throw new BaseFrameworkException(e, "���ƶ���ʱ�ر����ӳ���");
            }
        }
    }

    /**
     * ����һ��ȫ�µ�Serializable�������л��Ķ���
     * 
     * @param obj
     * @param theClass
     * @return
     * @throws Exception
     */
    public static <T> T copy(Serializable obj, Class<T> theClass) {
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream bais = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            baos.flush();

            byte[] content = baos.toByteArray();
            bais = new ByteArrayInputStream(content);
            ois = new ObjectInputStream(bais);
            return theClass.cast(ois.readObject());
        } catch (Throwable e) {
            throw new BaseFrameworkException(e, "���ƶ������");
        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (bais != null)
                    bais.close();
                if (oos != null)
                    oos.close();
                if (ois != null)
                    ois.close();
            } catch (Throwable e) {
                throw new BaseFrameworkException(e, "���ƶ���ʱ�ر����ӳ���");
            }
        }
    }
}
