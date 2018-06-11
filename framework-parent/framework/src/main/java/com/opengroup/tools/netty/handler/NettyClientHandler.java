/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.tools.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opengroup.tools.date.DateFormatterEnum;
import com.opengroup.tools.log.LogUtil;
import com.opengroup.tools.netty.beans.NettyMessage;
import com.opengroup.tools.netty.enums.NettyMsgGroupEnum;
import com.opengroup.tools.netty.enums.NettyMsgTypeEnum;
import com.opengroup.tools.netty.pool.NettyClientHanlderContextPool;
import com.opengroup.tools.netty.pool.NettyMessagePool;
import com.opengroup.tools.netty.util.ThreadUtil;

/**
 * 
 * @author Gushu
 * @version $Id: NettyClientHandler.java, v 0.1 2017年3月14日 下午5:52:21 Gushu Exp $
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private Logger                        logger    = Logger.getLogger(NettyClientHandler.class);

    private static final String           EMPTY_STR = "";

    private NettyClientHanlderContextPool handlerContextPool;

    public NettyClientHandler(NettyClientHanlderContextPool handlerContextPool) {
        this.handlerContextPool = handlerContextPool;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        this.handlerContextPool.offerClientHandlerContext(ctx, true);
        LogUtil.info(logger, "New NettyClient is created, its channel id:"
                             + getChannelShortIdStr(ctx));
    }

    private String getChannelShortIdStr(final ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        if (channel == null) {
            return EMPTY_STR;
        }
        ChannelId channelId = channel.id();
        if (channelId == null) {
            return EMPTY_STR;
        }
        return channelId.asShortText();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClient " + getChannelShortIdStr(ctx) + " :inactive");
        this.handlerContextPool.removeClientHandlerContext(ctx);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LogUtil.info(logger, "Client Channel id {" + getChannelShortIdStr(ctx) + "} received msg :"
                             + JSON.toJSONString(msg));
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("NettyClient " + getChannelShortIdStr(ctx)
                           + " :exceptionCaught, exception: " + cause.getMessage());
        this.handlerContextPool.removeClientHandlerContext(ctx);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        handleIdleStateEvent(ctx, evt);
    }

    private void handleIdleStateEvent(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            NettyMessage heartBeatMsg = new NettyMessage();
            heartBeatMsg.setContent(generateHeartBeatMsg());
            heartBeatMsg.setMsgType(NettyMsgTypeEnum.HEART);
            heartBeatMsg.setSource(NettyMsgGroupEnum.NETTY_CLIENT);

            switch (event.state()) {
                case WRITER_IDLE:
                case READER_IDLE:
                case ALL_IDLE:
                    if (NettyMessagePool.hasUnhandledMsg()) {
                        writeUnhandledMsg(ctx);
                        return;
                    }
                    ctx.writeAndFlush(heartBeatMsg);
            }
        }
    }

    private String generateHeartBeatMsg() {
        String dateStr = DateFormatterEnum.DATE_WITH_TIME.format(new Date());
        return dateStr + " : heartbeat ping";
    }

    private void writeUnhandledMsg(ChannelHandlerContext context) {
        LogUtil.info(logger, "start write unhandled message");

        int currentSize = NettyMessagePool.unhandledMsgList.size();
        do {
            NettyMessage message = NettyMessagePool.unhandledMsgList.poll();
            context.writeAndFlush(message);
            currentSize = NettyMessagePool.unhandledMsgList.size();
            if (currentSize > 0) {
                ThreadUtil.sleep();
            }
        } while (!NettyMessagePool.unhandledMsgList.isEmpty());

        LogUtil.info(logger, "end write unhandled message");
    }

    //    private void writeUnhandledMsg(ChannelHandlerContext context) {
    //        LogUtil.info(logger, "start write unhandled message");
    //        context.writeAndFlush(NettyMessagePool.unhandledMsgList);
    //        NettyMessagePool.unhandledMsgList.clear();
    //        LogUtil.info(logger, "end write unhandled message");
    //    }

}
