package com.opengroup.tools.http;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * ��׼�Ľ��
 * 
 * @author jin
 * @version $Id: CommonResponse.java, v 0.1 2015��6��15�� ����7:09:56 jin Exp $
 */
public class CommonResponse<T> {
    /**
     * �Ƿ�״̬
     */
    private boolean             success;
    /**
     * ������Ϣ
     */
    private String              errMsg;
    /**
     * ������
     */
    private String              errCode;

    /**
     * json��Ϣ��
     */
    private T                   body;

    private boolean             errSerious;
    /**
     * ָ��FastJson �����л�ʱ������
     */
    private SerializerFeature[] serializerFeatures;

    /**
     * �µĻỰID,һ�������ڵ�½֮�󷵻�
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
