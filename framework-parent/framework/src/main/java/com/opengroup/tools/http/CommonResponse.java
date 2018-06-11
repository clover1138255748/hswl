package com.opengroup.tools.http;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 标准的结果
 * 
 * @author jin
 * @version $Id: CommonResponse.java, v 0.1 2015年6月15日 下午7:09:56 jin Exp $
 */
public class CommonResponse<T> {
    /**
     * 是否状态
     */
    private boolean             success;
    /**
     * 错误消息
     */
    private String              errMsg;
    /**
     * 错误码
     */
    private String              errCode;

    /**
     * json消息体
     */
    private T                   body;

    private boolean             errSerious;
    /**
     * 指定FastJson 在序列化时的特性
     */
    private SerializerFeature[] serializerFeatures;

    /**
     * 新的会话ID,一般在用在登陆之后返回
     */
    private String              newSessionId;

    public SerializerFeature[] querySerializerFeatures() {
        return serializerFeatures;
    }

    public void putSerializerFeatures(SerializerFeature[] serializerFeatures) {
        this.serializerFeatures = serializerFeatures;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNewSessionId() {
        return newSessionId;
    }

    public void setNewSessionId(String newSessionId) {
        this.newSessionId = newSessionId;
    }

    public boolean isErrSerious() {
        return errSerious;
    }

    public void setErrSerious(boolean errSerious) {
        this.errSerious = errSerious;
    }

}
