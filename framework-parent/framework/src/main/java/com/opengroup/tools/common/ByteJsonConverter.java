package com.opengroup.tools.common;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * ����fastjson ת��������ֽ�����
 * 
 * @author David
 * @version $Id: ByteJsonConverter.java, v 0.1 2017��2��23�� ����3:50:19 David Exp $
 */
public class ByteJsonConverter {

	static Logger logger = Logger.getLogger(ByteJsonConverter.class);

	/**
	 * �ֽ�����ת���� object
	 * 
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	public static <T> Object byteToObject(byte[] bytes, Class<T> clazz) {
		if (null == bytes) {
			return null;
		}
		String line = null;
		try {
			line = new String(bytes, "UTF-8");
		} catch (Exception e) {
			logger.error(e);
			try {
				throw e;
			} catch (Exception e1) {
			}
		} finally {
		}
		return JSON.parseObject(line, clazz);
	}

    public static <T> List<T> byteToObjectList(byte[] bytes, Class<T> clazz) {
        if (null == bytes) {
            return null;
        }
        String line = null;
        try {
            line = new String(bytes, "UTF-8");
        } catch (Exception e) {
            logger.error(e);
            try {
                throw e;
            } catch (Exception e1) {
            }
        } finally {
        }
        return JSONArray.parseArray(line, clazz);
    }

	/**
	 * object ת���� �ֽ�����
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] objectToByte(Object obj) {
		if (null == obj) {
			return null;
		}
		byte[] bytes = null;
		try {
			String line = JSON.toJSONString(obj);
			bytes = line.getBytes("UTF-8");

		} catch (Exception e) {
			logger.error(e);
			try {
				throw e;
			} catch (Exception e1) {
			}
		} finally {
		}
		return (bytes);
	}
}
