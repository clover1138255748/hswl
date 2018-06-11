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
 * ����jvmͬ����Ϣ����Ϣ������
 * 
 * @author ruimin.jrm
 * @version $Id: JvmAsynEventPublisher.java, v 0.1 2015��6��4�� ����8:37:42 ruimin.jrm Exp $
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
     * ������Ϣ�� ���ڰѸ���Ϣ��Ӧ�� {@link IEventHandler}ȫ��ִ��һ��doHandler����
     * @see com.opengroup.middleware.event.IEventPublisher#publish(com.opengroup.middleware.event.UniformEvent)
     */
    public static void publish(UniformEvent event) {

        LogUtil.debug(logger, "��ʼ����jvm�첽��Ϣ", event);
        Set<IEventHandler> set = JvmAsynHandlerRegister.fetchHandlers(event.getTopic(),
            event.getEventCode());
        if (set != null) {
            for (final IEventHandler handler : set) {
                //�����ڴ��и���һ��ȫ�µĶ����Ա�֤����֮�䲻���໥���ġ�
                final UniformEvent newUe = BeanUtil.copy(event, UniformEvent.class);
                LogUtil.debug(logger, "ִ��jvm�첽doHandler", handler.getClass());
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
