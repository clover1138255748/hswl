/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.opengroup.common.anno.WebRuleTag;
import com.opengroup.tools.http.ServletContextHolder;
import com.opengroup.tools.string.StringUtil;

/**
 * 日志工具的拦截器，主要是在请求进入的时候，把日志的统一标识码注入
 * @author jin
 * @version $Id: LogUtilInterceptor.java, v 0.1 2015年7月16日 上午11:27:41 jin Exp $
 */
public class LogUtilInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(LogUtilInterceptor.class);

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        ServletContextHolder.store(request, response);
        LogUtil.myThreadStart(null);
        LogUtil.info(logger,
            Thread.currentThread().getName() + " visitURL:" + request.getRequestURL() + "?"
                    + convertReqParam(request.getParameterMap()));
        PerfLog.printStart(getMethodStr(handler) + "(WEB):" + request.getServletPath());
        return checkRule(handler, request.getRequestURL().toString());
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        PerfLog.printEnd(getMethodStr(handler) + "(WEB):" + request.getServletPath());
        LogUtil.myThreadEnd();
        ServletContextHolder.clean();
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        if (LogUtil.getUniqueId() != null) {
            PerfLog.printEnd(getMethodStr(handler) + "(WEB):" + request.getServletPath());
            LogUtil.myThreadEnd();
            ServletContextHolder.clean();
        }
    }

    /**
     * 取得handler的类与方法名
     * 
     * @param handler
     * @return
     */
    private String getMethodStr(Object handler) {
        if (!(handler instanceof HandlerMethod)){
            return handler.getClass().getName();
        }
        HandlerMethod hm = (HandlerMethod) handler;
        String className = hm.getBean().getClass().getName();
        String methodName = hm.getMethod().getName();
        return className + "." + methodName;
    }

    /**
     * 检测是否符合定义的访问规则
     * 
     * @param handler
     * @param requestUrl
     * @return
     */
    private boolean checkRule(Object handler, String requestUrl) {
        //Websocket 请求不进行拦截
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod hm = (HandlerMethod) handler;
        String methodName = hm.getMethod().getName();

        Method targetMethod = null;
        Method[] ms = hm.getBean().getClass().getDeclaredMethods();
        if (ms != null) {
            for (Method m : ms) {
                if (StringUtil.equals(methodName, m.getName())) {
                    targetMethod = m;
                }
            }
        }
        if (targetMethod != null) {
            Annotation[] annos = targetMethod.getDeclaredAnnotations();
            if (annos != null) {
                for (Annotation anno : annos) {
                    if (anno.annotationType() == WebRuleTag.class) {
                        WebRuleTag ruleTag = (WebRuleTag) anno;
                        String rule = ruleTag.ruleRex();
                        if (!StringUtil.isBlank(rule)) {
                            return Pattern.matches(rule, requestUrl);
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 把请求中的数据转化成String输出
     * 
     * @param map
     * @return
     */
    private String convertReqParam(Map<String, String[]> map) {
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                sb.append(entry.getKey() + ":" + StringUtil.arrayToString(entry.getValue()) + ";;");
            }
            return sb.toString();
        }
        return null;
    }
}
