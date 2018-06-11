/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.http;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.opengroup.exception.BaseFrameworkException;
import com.opengroup.exception.UserKickException;
import com.opengroup.tools.date.DateFormatterEnum;
import com.opengroup.tools.date.DateUtil;
import com.opengroup.tools.kv.KvClient;
import com.opengroup.tools.log.LogUtil;
import com.opengroup.tools.random.UniqueIdGenerator;
import com.opengroup.tools.string.StringUtil;

/**
 * http��session��֧��
 * @author jin
 * @version $Id: SessionContextHolder.java, v 0.1 2015��8��12�� ����4:25:42 jin Exp $
 */
public class SessionSupport {
    private static Logger         logger                 = Logger.getLogger(SessionSupport.class);
    public static String          SESSION_ID_KEY         = "sessionId";

    public static String          LOGIN_USER_SESSION_KEY = "login";

    public static String          LOGIN_USER_ID_KEY      = "uid";
    /**
     * kv���DB�ͻ���
     */
    @Autowired
    private KvClient              kvClient;
    @Value("#{framework_properties['framework.tool.http.session.expried_seconds']}")
    private int                   sessionExpiredSeconds;
    @Value("#{framework_properties['framework.tool.http.session.deprecated_seconds']}")
    private int deprecatedExpiredSeconds = 600;
    /**
     * ����ĵ���
     */
    private static SessionSupport self;

    public SessionSupport() {
        self = this;
    }

    /**
     * ȡ������
     * 
     * @return
     */
    public static SessionSupport getInstance() {
        return self;
    }

    /**
     * ȡsession��ֵ
     * @param request
     * @param key
     * @param c
     * @return
     */
    public <T> T getSessionValue(HttpServletRequest request, String key, Class<T> c) {
        String sessionId = getSessionId(request);
        key = "sid_" + sessionId + "_" + key;
        return kvClient.read(key, c);
    }
    /**
     * ȡsession��ֵ
     * @param request
     * @param cellphone  �ֻ���
     * @param key
     * @param c
     * @return
     */
    public <T> T getSessionValue(HttpServletRequest request, String cellphone ,String key, Class<T> c) {
        String sessionId = getSessionId(request);
        key = "sid_" + sessionId + "_" + cellphone + "_" + key;
        return kvClient.read(key, c);
    }

    /**
     * ����session��ֵ
     * 
     * @param request
     * @param key
     * @param value
     */
    public <V> void setSessionValue(HttpServletRequest request, String key, V value,
                                    int expiredSeconds) {
        String sessionId = getSessionId(request);
        if (StringUtil.isBlank(sessionId)) {
            throw new BaseFrameworkException("sessionId����Ϊ��");
        }
        key = "sid_" + sessionId + "_" + key;
        kvClient.delete(key);
        kvClient.save(key, value, expiredSeconds);
    }

    /**
     * ����session��ֵ, Ĭ�Ϲ���ʱ����һ��
     * 
     * @param request
     * @param key
     * @param value
     */
    public <V> void setSessionValue(HttpServletRequest request, String key, V value) {
        String sessionId = getSessionId(request);
        if (StringUtil.isBlank(sessionId)) {
            throw new BaseFrameworkException("sessionId����Ϊ��");
        }
        key = "sid_" + sessionId + "_" + key;
        kvClient.save(key, value, sessionExpiredSeconds);
    }

    /**
     * ɾ��session�е�ĳ��ֵ
     * 
     * @param request
     * @param key
     */
    public void deleteSessionValue(HttpServletRequest request, String key) {
        String sessionId = getSessionId(request);
        key = "sid_" + sessionId + "_" + key;
        kvClient.delete(key);
    }

    /**
     * ���û���½����Ϣ���뵽�û���session�У�ͬʱ��ԭ�е��û�session����Ϣɾ����
     * 
     * @param userId
     * @param userDtoKey
     * @param request
     */
    public void putLoginUserInfoInSession(String userId, Object userInfo, HttpServletRequest request) {
        String uidKey = "uid_" + userId;
        String originalSid = kvClient.read(uidKey, String.class);
        String currentSid = getSessionId(request);
        if (!StringUtil.isBlank(originalSid)) {
            String originalSessionKey = "sid_" + originalSid + "_" + LOGIN_USER_SESSION_KEY;
            String originalSessionUidKey = "sid_" + originalSid + "_" + LOGIN_USER_ID_KEY;
            
            kvClient.delete(originalSessionKey);
            kvClient.delete(originalSessionUidKey);
            LogUtil.info(logger, "putLoginUserInfoInSession,user relogin, originalSessionKey:"
                                 + originalSessionKey);
            String oldOriginalSid = "old_" + originalSid;
            
            kvClient.save(oldOriginalSid, "1", deprecatedExpiredSeconds);
            LogUtil.info(logger, "putLoginUserInfoInSession,save old key=" + oldOriginalSid);
        }
        String newSessionKey = "sid_" + currentSid + "_" + LOGIN_USER_SESSION_KEY;
        String newSessionKeyUid = "sid_" + currentSid + "_" + LOGIN_USER_ID_KEY;
        kvClient.save(newSessionKey, userInfo, sessionExpiredSeconds);
        kvClient.save(uidKey, currentSid, Integer.MAX_VALUE);
        kvClient.save(newSessionKeyUid, userId, sessionExpiredSeconds);
        LogUtil.info(logger, "putLoginUserInfoInSession,user[" + userId + "] login, newSessionKey:"
                             + newSessionKey);
    }

    /**
     * ȡ�û���½����Ϣ
     * 
     * @param request
     * @param c
     * @return
     */
    public <T> T fetchLoginUserInfoInSession(HttpServletRequest request, Class<T> c) {
        String sessionId = getSessionId(request);
        String key = "sid_" + sessionId + "_" + LOGIN_USER_SESSION_KEY;
        T result = kvClient.read(key, c);
        if (result == null) {
        	checkOld(sessionId);
            LogUtil.info(logger, "fetchLoginUserInfoInSession, user not login, sessionKey:" + key);
        }
        return result;
    }

    /**
     * ��ѯ��½�û���ID
     * 
     * @param request
     * @return
     */
    public String fetchLoginUserIdInSession(HttpServletRequest request) {
        String sessionId = getSessionId(request);
        String key = "sid_" + sessionId + "_" + LOGIN_USER_ID_KEY;
        String uid = kvClient.read(key, String.class);
        if (uid == null) {
        	checkOld(sessionId);
            LogUtil.info(logger, "fetchLoginUserIdInSession, user not login, sessionKey:" + key);
        }
        return uid;
    }
    
    private void checkOld(String sessionId) {
    	String oldKey = "old_" + sessionId;
    	LogUtil.info(logger, "checkOld,key=" + oldKey);
    	String oldValue = kvClient.read(oldKey, String.class);
    	if(oldValue != null) {
    		kvClient.delete(oldKey);
    		// throw new BizException(BaseFrameworkException.USER_KICK_EXCEPTION, "�û����߳�");
    		throw new UserKickException("�û����߳�");
    	}
    }

    /**
     * �û��˳���½ʱ��ɾ����½��Ϣ
     * 
     * @param request
     */
    public void deleteLogoutInfoInSession(HttpServletRequest request) {
        String sessionId = getSessionId(request);
        String key = "sid_" + sessionId + "_" + LOGIN_USER_SESSION_KEY;
        String uidKey = "sid_" + sessionId + "_" + LOGIN_USER_ID_KEY;
        kvClient.delete(key);
        kvClient.delete(uidKey);
        LogUtil.info(logger, "user logonout, sessionKey:" + key);
    }

    /**
     * ȡ�û���sessionID�� 
     * 1 ȡrequest���еİ�ͷ��Ϣ
     * 2 ȡrequest��Attribute�е���Ϣ
     * 3 �Զ����ɣ����洢��request��Attribute��
     * 
     * @param request
     * @return
     */
    public static String getSessionId(HttpServletRequest request) {
        String sessionId = request.getHeader(SESSION_ID_KEY);
        if (StringUtil.isBlank(sessionId)) {
            sessionId = request.getParameter(SESSION_ID_KEY);
            if (StringUtil.isBlank(sessionId)) {
                sessionId = (String) request.getAttribute(SESSION_ID_KEY);
                if (StringUtil.isBlank(sessionId)) {
                    String dateStr = DateUtil.format(new Date(),
                        DateFormatterEnum.SIMPLE_WITHOUT_SEP.getCode());
                    sessionId = dateStr + UniqueIdGenerator.generateRandomStr(15);
                    request.setAttribute(SESSION_ID_KEY, sessionId);
                }
            }
        }
        return sessionId;
    }
    
    public int getDeprecatedExpiredSeconds() {
    	return deprecatedExpiredSeconds;
    }
    
    public void setDeprecatedExpiredSeconds(int deprecatedExpiredSeconds) {
    	this.deprecatedExpiredSeconds = deprecatedExpiredSeconds;
    }

    public <V> void setSessionValue(HttpServletRequest request,String cellphone, String key, V value,
                                    int expiredSeconds) {
        String sessionId = getSessionId(request);
        if (StringUtil.isBlank(sessionId)) {
            throw new BaseFrameworkException("sessionId����Ϊ��");
        }
        key = "sid_" + sessionId + "_" + cellphone + "_" + key;
        kvClient.delete(key);
        kvClient.save(key, value, expiredSeconds);
    }
}
