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
 * 从Classpath中读取内容的工具类
 * @author ruimin.jrm
 * @version $Id: ClasspathFileUtil.java, v 0.1 2015年4月3日 上午11:20:18 ruimin.jrm Exp $
 */
public class ClasspathFileUtil {

    public static URI readAsUri(String filename) throws Exception {
        return ClasspathFileUtil.class.getClassLoader().getResource(filename).toURI();
    }

    /**
     * 以流的方式读出
     * 
     * @param filename
     * @return
     * @throws Exception
     */
    public static InputStream readAsStream(String filename) throws Exception {
        return ClasspathFileUtil.class.getClassLoader().getResourceAsStream(filename);
    }

    /**
     * 从classpath中读取txt文件的内容，返回结果为一个删掉换行符的字符串
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
     * 从classpath中读取txt文件的内容，每行字符串是数组中的一个元素
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
