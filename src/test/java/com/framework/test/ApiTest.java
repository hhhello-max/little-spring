package com.framework.test;

import com.framework.beans.PropertyValue;
import com.framework.beans.PropertyValues;
import com.framework.beans.factory.config.BeanDefinition;
import com.framework.beans.factory.BeanFactory;
import com.framework.beans.factory.support.DefaultListableBeanFactory;
import com.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.test.bean.Man;
import com.framework.test.bean.UserService;
import com.framework.test.common.MyBeanFactoryPostProcessor;
import com.framework.test.common.MyBeanPostProcessor;
import com.framework.test.event.CustomEvent;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testInfo(){

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        //userService.info();

        UserService userService2 = (UserService) beanFactory.getBean("userService");
        //userService2.info();

        System.out.println(userService2 == userService);
    }

    @Test
    public void testApplyProperty(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "tom"));
        propertyValues.addPropertyValue(new PropertyValue("age", 18));
        propertyValues.addPropertyValue(new PropertyValue("sex", "male"));
        BeanDefinition beanDefinition = new BeanDefinition(Man.class, propertyValues);
        beanFactory.registryBeanDefinition("man", beanDefinition);

        Man man = (Man) beanFactory.getBean("man");
        System.out.println(man);
    }

    @Test
    public void testXml(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("classpath:spring.xml");

        Man man = (Man) beanFactory.getBean("man");
        System.out.println(man);

    }

    @Test
    public void test_xml2() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 2. 获取Bean对象调用方法
        //Man man = applicationContext.getBean("man", Man.class);
        //System.out.println(man);
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());

        System.out.println(userService.getApplicationContext());
        System.out.println(userService.getBeanFactory());

    }

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void testEvent(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        applicationContext.publishEvent(new CustomEvent(applicationContext, 66666L, "success"));

        applicationContext.registerShutdownHook();
    }

}
