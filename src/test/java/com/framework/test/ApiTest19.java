package com.framework.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.framework.aop.AdvisedSupport;
import com.framework.aop.TargetSource;
import com.framework.aop.aspectj.AspectJExpressionPointcut;
import com.framework.aop.proxy.Cglib2AopProxy;
import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.jdbc.core.JdbcTemplate;
import com.framework.jdbc.datasource.DataSourceTransactionManager;
import com.framework.test.tx.JdbcService;
import com.framework.transaction.annotation.AnnotationTransactionAttributeSource;
import com.framework.transaction.interceptor.TransactionInterceptor;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ApiTest19 {

    private JdbcTemplate jdbcTemplate;
    private JdbcService jdbcService;
    private DataSource dataSource;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:jdbc.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        dataSource = applicationContext.getBean(DruidDataSource.class);
        jdbcService = applicationContext.getBean(JdbcService.class);
    }

    @Test
    public void test_Transaction() throws SQLException {
        AnnotationTransactionAttributeSource transactionAttributeSource = new AnnotationTransactionAttributeSource();
        transactionAttributeSource.findTransactionAttribute(jdbcService.getClass());

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, transactionAttributeSource);

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(jdbcService));
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.framework.test.tx.JdbcService.*(..))"));

        // 代理对象(Cglib2AopProxy)
        JdbcService proxy_cglib = (JdbcService) new Cglib2AopProxy(advisedSupport).getProxy();

        // 测试调用，有事务【不能同时提交2条有主键冲突的数据】
        proxy_cglib.saveData(jdbcTemplate);

        // 测试调用，无事务【提交2条有主键冲突的数据成功一条】
        //proxy_cglib.saveDataNoTransaction(jdbcTemplate);
    }

}
