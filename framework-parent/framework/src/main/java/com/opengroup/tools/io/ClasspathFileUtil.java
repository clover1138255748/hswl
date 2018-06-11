/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * ��Classpath�ж�ȡ���ݵĹ�����
 * @author ruimin.jrm
 * @version $Id: ClasspathFileUtil.java, v 0.1 2015��4��3�� ����11:20:18 ruimin.jrm Exp $
 */
public class ClasspathFileUtil {

    public static URI readAsUri(String filename) throws Exception {
        return ClasspathFileUtil.class.getClassLoader().getResource(filename).toURI();
    }

    /**
     * �����ķ�ʽ����
     * 
     * @param filename
     * @return
     * @throws Exception
     */
    public static InputStream readAsStream(String filename) throws Exception {
        return ClasspathFileUtil.class.getClassLoader().getResourceAsStream(filename);
    }

    /**
     * ��classpath�ж�ȡtxt�ļ������ݣ����ؽ��Ϊһ��ɾ�����з����ַ���
     * 
     * @param filename
     * @return
     * @throws Exception
     */
    public static String readAsStr(String filename) throws Exception {
        InputStream stream = readAsStream(filename);
        return IOUtil.readAsStringWithoutLine(stream);
    }

    /**
     * ��classpath�ж�ȡtxt�ļ������ݣ�ÿ���ַ����������е�һ��Ԫ��
     * 
     * @param filename
     * @return
     * @throws Exception
     */
    public static List<String> readListPerLine(String filename) throws Exception {
        List<String> list = new ArrayList<String>();
        InputStream stream = null;
        BufferedReader breader = null;
        InputStreamReader reader = null;
        try {
            stream = ClasspathFileUtil.class.getClassLoader().getResourceAsStream(filename);
            reader = new InputStreamReader(stream);
            breader = new BufferedReader(reader);
            while (true) {
                String line = breader.readLine();
                if (line == null)
                    break;
                list.add(line);
            }
        } finally {
            if (stream != null)
                stream.close();
            if (reader != null)
                reader.close();
            if (breader != null)
                breader.close();
        }
        return list;
    }

}
