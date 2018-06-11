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
 * http中session的支持
 * @author jin
 * @version $Id: SessionContextHolder.java, v 0.1 2015年8月12日 下午4:25:42 jin Exp $
 */
public class SessionSupport {
    private static Logger         logger                 = Logger.getLogger(SessionSupport.class);
    public static String          SESSION_ID_KEY         = "sessionId";

    public static String          LOGIN_USER_SESSION_KEY = "login";

    public static String          LOGIN_USER_ID_KEY      = "uid";
    /**
     * kv类的DB客户端
     */
    @Autowired
    private KvClient              kvClient;
    @Value("#{framework_properties['framework.tool.http.session.expried_seconds']}")
    private int                   sessionExpiredSeconds;
    @Value("#{framework_properties['framework.tool.http.session.deprecated_seconds']}")
    private int deprecatedExpiredSeconds = 600;
    /**
     * 自身的单例
     */
    private static SessionSupport self;

    public SessionSupport() {
        self = this;
    }

    /**
     * 取得自身
     * 
     * @return
     */
    public static SessionSupport getInstance() {
        return self;
    }

    /**
     * 取session的值
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
     * 取session的值
     * @param request
     * @param cellphone  手机号
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
     * 设置session的值
     * 
     * @param request
     * @param key
     * @param value
     */
    public <V> void setSessionValue(HttpServletRequest request, String key, V value,
                                    int expiredSeconds) {
        String sessionId = getSessionId(request);
        if (StringUtil.isBlank(sessionId)) {
            throw new BaseFrameworkException("sessionId不可为空");
        }
        key = "sid_" + sessionId + "_" + key;
        kvClient.delete(key);
        kvClient.save(key, value, expiredSeconds);
    }

    /**
     * 设置session的值, 默认过期时间是一年
     * 
     * @param request
     * @param key
     * @param value
     */
    public <V> void setSessionValue(HttpServletRequest request, String key, V value) {
        String sessionId = getSessionId(request);
        if (StringUtil.isBlank(sessionId)) {
            throw new BaseFrameworkException("sessionId不可为空");
        }
        key = "sid_" + sessionId + "_" + key;
        kvClient.save(key, value, sessionExpiredSeconds);
    }

    /**
     * 删除session中的某个值
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
     * 把用户登陆的信息放入到用户的session中，同时把原有的用户session的信息删掉。
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
     * 取用户登陆的信息
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
     * 查询登陆用户的ID
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
    		// throw new BizException(BaseFrameworkException.USER_KICK_EXCEPTION, "用户被踢除");
    		throw new UserKickException("用户被踢除");
    	}
    }

    /**
     * 用户退出登陆时，删除登陆信息
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
     * 取用户的sessionID， 
     * 1 取request的中的包头信息
     * 2 取request的Attribute中的信息
     * 3 自动生成，并存储到request的Attribute中
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
            throw new BaseFrameworkException("sessionId不可为空");
        }
        key = "sid_" + sessionId + "_" + cellphone + "_" + key;
        kvClient.delete(key);
        kvClient.save(key, value, expiredSeconds);
    }
}
