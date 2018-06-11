package com.opengroup.tool.sequence;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础测试类
 * 
 * @author jin
 * @version $Id: BaseTestLoader.java, v 0.1 2015年6月23日 下午2:36:30 jin Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:framework/spring/framework.xml" })
public abstract class BaseTest extends AbstractJUnit4SpringContextTests {

}
