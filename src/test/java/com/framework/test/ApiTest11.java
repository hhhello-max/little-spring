package com.framework.test;

import com.framework.aop.AdvisedSupport;
import com.framework.aop.TargetSource;
import com.framework.aop.aspectj.AspectJExpressionPointcut;
import com.framework.aop.proxy.Cglib2AopProxy;
import com.framework.aop.proxy.JdkDynamicAopProxy;
import com.framework.test.pointcutTest.BookService;
import com.framework.test.pointcutTest.BookServiceInterceptor;
import com.framework.test.pointcutTest.IBookService;
import org.junit.Test;

public class ApiTest11 {

    @Test
    public void test_dynamic() {

        // 目标对象
        IBookService bookService = new BookService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(bookService));
        advisedSupport.setMethodInterceptor(new BookServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.framework.test.pointcutTest.IBookService.*(..))"));

        // 目标对象
        BookService bookService2 = new BookService();
        // 组装代理信息
        AdvisedSupport advisedSupport2 = new AdvisedSupport();
        advisedSupport2.setTargetSource(new TargetSource(bookService2));
        advisedSupport2.setMethodInterceptor(new BookServiceInterceptor());
        advisedSupport2.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.framework.test.pointcutTest.BookService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IBookService proxy_jdk = (IBookService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        proxy_jdk.bookmark("123");

        // 代理对象(Cglib2AopProxy)
        BookService proxy_cglib = (BookService) new Cglib2AopProxy(advisedSupport2).getProxy();
        // 测试调用
        proxy_cglib.bookmark("456");
    }

    /*@Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new UserService();

        // AOP 代理
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* cn.bugstack.springframework.test.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        System.out.println("拦截到了");
                        long start = System.currentTimeMillis();
                        try {
                            System.out.println("拦截中...");
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });

        String result = proxy.queryUserInfo();
        System.out.println("测试结果：" + result);

    }*/

}
