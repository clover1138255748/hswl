package com.opengroup.middleware.rpc;

import org.junit.Assert;
import org.junit.Test;

import com.opengroup.middleware.rpc.consumer.FacadeConsumer;
import com.opengroup.middleware.rpc.provider.FacadeProvider;

/**
 * 远程方法调用的统一形式。
 * 
 * @author ruimin.jrm
 * @version $Id: RpcTest.java, v 0.1 2015年6月5日 下午5:28:49 ruimin.jrm Exp $
 */
public class RpcTest {

    private static String version = "1.0.0";

    @Test
    public void normalTest() {
        try {
            FacadeProvider fp = new FacadeProvider();
            fp.setDelegateInterface(IRpcTestBiz.class.getName());
            fp.setVersion(version);
            fp.init();
            fp.setTarget(new RpcTestBizImpl());

            FacadeConsumer fc = new FacadeConsumer();
            fc.setDelegateInterface(IRpcTestBiz.class.getName());
            fc.setVersion(version);
            IRpcTestBiz obj = (IRpcTestBiz) fc.init();

            String result = obj.test();
            Assert.assertEquals(result, "testA");
        } catch (Throwable e) {
            Assert.fail("exception:" + e.toString());
        }
    }

}
