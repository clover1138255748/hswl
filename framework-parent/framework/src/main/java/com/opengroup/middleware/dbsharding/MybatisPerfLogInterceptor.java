/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.opengroup.tools.log.PerfLog;

/**
 * mybatis的性能日志工具
 * @author jin
 * @version $Id: MybatisPerfLogInterceptor.java, v 0.1 2015年7月21日 下午2:00:58 jin Exp $
 */
@Intercepts({
             @Signature(type = Executor.class, method = "query", args = { MappedStatement.class,
                                                                         Object.class,
                                                                         RowBounds.class,
                                                                         ResultHandler.class }),
             @Signature(type = Executor.class, method = "update", args = { MappedStatement.class,
                                                                          Object.class }), })
public class MybatisPerfLogInterceptor implements Interceptor {
    /** 
     * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object ms = null;
        MappedStatement mappedStatement = null;
        String invoker = null;
        ShardingDatasource shardingDs = null;
        Object parameter = null;
        if (invocation.getArgs() != null && invocation.getArgs().length > 0) {
            ms = invocation.getArgs()[0];
        }
        if (invocation.getArgs() != null && invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        if (ms != null && ms instanceof MappedStatement) {
            mappedStatement = (MappedStatement) ms;
            invoker = mappedStatement.getId() + "(DAL)";
        }

        try {
            if (mappedStatement != null) {
                DataSource ds = mappedStatement.getConfiguration().getEnvironment().getDataSource();
                //如果是自定义的分库分表的数据源，那么执行分库分表的操作
                if (ds instanceof ShardingDatasource) {
                    ShardingDatasource shardingDS = (ShardingDatasource) ds;
                    if (shardingDS.needSharding()) {
                        BoundSql bsql = mappedStatement.getBoundSql(parameter);
                        shardingDs = (ShardingDatasource) ds;
                        String sqlAfterSharding = shardingDs.doRouter(bsql.getSql());
                        /**
                         * TODO  换SQL
                         */
                    }
                }
            }
            if (invoker != null)
                PerfLog.printStart(invoker);
            return invocation.proceed();
        } finally {
            if (shardingDs != null)
                shardingDs.removeRouter();
            if (invoker != null)
                PerfLog.printEnd(invoker);
        }
    }

    /** 
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /** 
     * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties arg0) {
    }

}
