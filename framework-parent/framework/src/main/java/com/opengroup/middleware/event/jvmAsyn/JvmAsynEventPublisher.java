package com.opengroup.middleware.event.jvmAsyn;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.opengroup.common.anno.ServiceTag;
import com.opengroup.middleware.event.IEventHandler;
import com.opengroup.middleware.event.UniformEvent;
import com.opengroup.tools.io.BeanUtil;
import com.opengroup.tools.log.LogUtil;
import com.opengroup.tools.log.PerfLog;

/**
 * 基于jvm同步消息的消息发布器
 * 
 * @author ruimin.jrm
 * @version $Id: JvmAsynEventPublisher.java, v 0.1 2015年6月4日 下午8:37:42 ruimin.jrm Exp $
 */
@ServiceTag
public class JvmAsynEventPublisher {

    private static Logger             logger     = Logger.getLogger(JvmAsynEventPublisher.class);

    private static ThreadPoolExecutor threadPool = null;
    static {
        int corePoolSize = 50;
        int maxPoolSize = 100;
        long keepAliveTime = 1L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
        threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit,
            workQueue);
    }

    /**
     * 发布消息。 等于把该消息对应的 {@link IEventHandler}全部执行一遍doHandler方法
     * @see com.opengroup.middleware.event.IEventPublisher#publish(com.opengroup.middleware.event.UniformEvent)
     */
    public static void publish(UniformEvent event) {

        LogUtil.debug(logger, "开始发布jvm异步消息", event);
        Set<IEventHandler> set = JvmAsynHandlerRegister.fetchHandlers(event.getTopic(),
            event.getEventCode());
        if (set != null) {
            for (final IEventHandler handler : set) {
                //先在内存中复制一份全新的对象，以保证对象之间不会相互串改。
                final UniformEvent newUe = BeanUtil.copy(event, UniformEvent.class);
                LogUtil.debug(logger, "执行jvm异步doHandler", handler.getClass());
                final String lastLogId = LogUtil.getUniqueId();
                threadPool.execute(new Runnable() {
                    public void run() {
                        try {
                            LogUtil.myThreadStart(lastLogId);
                            PerfLog.printStart(handler.getClass().getName() + ".doHandler(ASY_E)");
                            handler.doHandler(newUe);
                        } finally {
                            PerfLog.printEnd(handler.getClass().getName() + ".doHandler(ASY_E)");
                            LogUtil.myThreadEnd();
                        }
                    }
                });
            }
        }
    }
}
