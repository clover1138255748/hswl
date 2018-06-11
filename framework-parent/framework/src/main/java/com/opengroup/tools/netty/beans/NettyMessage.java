/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.tools.netty.beans;

import java.io.Serializable;

import com.opengroup.tools.netty.enums.NettyMsgGroupEnum;
import com.opengroup.tools.netty.enums.NettyMsgTopicEnum;
import com.opengroup.tools.netty.enums.NettyMsgTypeEnum;

/**
 * 
 * @author Gushu
 * @version $Id: NettyBaseMessage.java, v 0.1 2017年3月14日 下午4:23:32 Gushu Exp $
 */
public class NettyMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 消息类型 */
    private NettyMsgTypeEnum  msgType;

    /** 消息来源 */
    private NettyMsgGroupEnum source;

    /** 消息内容 */
    private String            content;

    /** 消息主题 */
    private NettyMsgTopicEnum     topic;

    /**
     * 当只需要给某些用户发送消息时，需要用到. 该值在 APP端跟Netty Server建立连接时候，传过来.
     */
    private String             userId;


    /**
     * default constructor is needed for fastjson
     */
    public NettyMessage() {
    }


    /**
     * @param msgType
     * @param source
     * @param content
     * @param topic
     * @param userId
     */
    public NettyMessage(NettyMsgTypeEnum msgType, NettyMsgGroupEnum source, String content,
                        NettyMsgTopicEnum topic, String userId) {
        super();
        this.msgType = msgType;
        this.source = source;
        this.content = content;
        this.topic = topic;
        this.userId = userId;
    }



    public NettyMsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(NettyMsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    public NettyMsgGroupEnum getSource() {
        return source;
    }

    public void setSource(NettyMsgGroupEnum source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NettyMsgTopicEnum getTopic() {
        return topic;
    }

    public void setTopic(NettyMsgTopicEnum topic) {
        this.topic = topic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
