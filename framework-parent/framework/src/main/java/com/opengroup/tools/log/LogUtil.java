package com.opengroup.tools.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opengroup.tools.random.UniqueIdGenerator;
import com.opengroup.tools.string.StringUtil;

/**
 * ��׼��־���ߣ�Ҫ����ҵ���У�����־������Ҫ��������еķ�����
 * 
 * @author ruimin.jrm
 * @version $Id: LogUtil.java, v 0.1 2015��6��4�� ����12:08:41 ruimin.jrm Exp $
 */
public class LogUtil {

    /**
     * ��־��Ψһ�ţ�Ϊ�˷���һ������ҵ���ɢ�ڶ����־�ļ��У� ��������������
     */
    private static ThreadLocal<String> logUniqueId = new ThreadLocal<String>();

    public static String getUniqueId() {
        return logUniqueId.get();
    }

    /**
     * ������־��Ψһ�ŵ��̱߳�����
     * 
     * @param previousLogId ��ʾ���̵߳�logId
     */
    public static void myThreadStart(String previousLogId) {
        if (StringUtil.isBlank(previousLogId)) {
            logUniqueId.set(UniqueIdGenerator.generateRandomStr(10));
        } else {
            logUniqueId.set(previousLogId + "_" + UniqueIdGenerator.generateRandomStr(3));
        }
    }

    /**
     * ����־��Ψһ������̱߳���
     */
    public static void myThreadEnd() {
        logUniqueId.remove();
    }

    /**
     * ����Logger.trace����
     * 
     * @param logger
     * @param msg
     */
    public static void trace(Logger logger, String msg) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg);
        }
    }

    /**
     * ����Logger.trace����
     * 
     * @param logger
     * @param msg
     */
    public static void trace(Logger logger, String msg, Object obj) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg + " | " + obj.toString());
        }
    }

    /**
     * ����Logger.trace����
     * 
     * @param logger
     * @param msg
     * @param t 
     */
    public static void trace(Logger logger, String msg, Throwable t) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg, t);
        }
    }

    /**
     * ����Logger.debug����
     * 
     * @param logger
     * @param msg
     */
    public static void debug(Logger logger, String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    /**
     * ����Logger.debug����
     * 
     * @param logger
     * @param msg
     */
    public static void debug(Logger logger, String msg, Object obj) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg + " | " + obj.toString());
        }
    }

    /**
     * ����Logger.debug����
     * 
     * @param logger
     * @param msg
     * @param t 
     */
    public static void debug(Logger logger, String msg, Throwable t) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, t);
        }
    }

    /**
     * ����Logger.info����
     * 
     * @param logger
     * @param msg
     */
    public static void info(Logger logger, String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

    /**
     * ����Logger.info����
     * 
     * @param logger
     * @param msg
     */
    public static void info(Logger logger, String msg, Object obj) {
        if (logger.isInfoEnabled()) {
            logger.info(msg + " | " + obj.toString());
        }
    }

    /**
     * ����Logger.info����
     * 
     * @param logger
     * @param msg
     * @param t 
     */
    public static void info(Logger logger, String msg, Throwable t) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, t);
        }
    }

    /**
     * ����Logger.warn����
     * 
     * @param logger
     * @param msg
     */
    public static void warn(Logger logger, String msg) {
        if (logger.isEnabledFor(Level.WARN)) {
            logger.warn(msg);
        }
    }

    /**
     * ����Logger.warn����
     * 
     * @param logger
     * @param msg
     */
    public static void warn(Logger logger, String msg, Object obj) {
        if (logger.isEnabledFor(Level.WARN)) {
            logger.warn(msg + " | " + obj.toString());
        }
    }

    /**
     * ����Logger.warn����
     * 
     * @param logger
     * @param msg
     * @param t 
     */
    public static void warn(Logger logger, String msg, Throwable t) {
        if (logger.isEnabledFor(Level.WARN)) {
            logger.warn(msg, t);
        }
    }

    /**
     * ����Logger.error����
     * 
     * @param logger
     * @param msg
     */
    public static void error(Logger logger, String msg) {
        if (logger.isEnabledFor(Level.ERROR)) {
            logger.error(msg);
        }
    }

    /**
     * ����Logger.error����
     * 
     * @param logger
     * @param msg
     */
    public static void error(Logger logger, String msg, Object obj) {
        if (logger.isEnabledFor(Level.ERROR)) {
            logger.error(msg + " | " + obj.toString());
        }
    }

    /**
     * ����Logger.error����
     * 
     * @param logger
     * @param msg
     * @param t 
     */
    public static void error(Logger logger, String msg, Throwable t) {
        if (logger.isEnabledFor(Level.ERROR)) {
            logger.error(msg, t);
        }
    }

    /**
     * ����Logger.fatal����
     * 
     * @param logger
     * @param msg
     */
    public static void fatal(Logger logger, String msg) {
        if (logger.isEnabledFor(Level.FATAL)) {
            logger.fatal(msg);
        }
    }

    /**
     * ����Logger.fatal����
     * 
     * @param logger
     * @param msg
     */
    public static void fatal(Logger logger, String msg, Object obj) {
        if (logger.isEnabledFor(Level.FATAL)) {
            logger.error(msg + " | " + obj.toString());
        }
    }

    /**
     * ����Logger.error����
     * 
     * @param logger
     * @param msg
     * @param t 
     */
    public static void fatal(Logger logger, String msg, Throwable t) {
        if (logger.isEnabledFor(Level.FATAL)) {
            logger.fatal(msg, t);
        }
    }

}
