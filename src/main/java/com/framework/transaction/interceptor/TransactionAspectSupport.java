package com.framework.transaction.interceptor;

import cn.hutool.core.thread.threadlocal.NamedThreadLocal;
import com.framework.beans.BeansException;
import com.framework.beans.factory.BeanFactory;
import com.framework.beans.factory.BeanFactoryAware;
import com.framework.beans.factory.InitializingBean;
import com.framework.transaction.PlatformTransactionManager;
import com.framework.transaction.TransactionStatus;
import com.framework.util.ClassUtils;

import java.lang.reflect.Method;

public abstract class TransactionAspectSupport implements BeanFactoryAware, InitializingBean {

    private static final ThreadLocal<TransactionInfo> transactionInfoHolder = new NamedThreadLocal<>("Current aspect-driven transaction");

    private BeanFactory beanFactory;

    private TransactionAttributeSource transactionAttributeSource;

    private PlatformTransactionManager transactionManager;

    protected Object invokeWithinTransaction(Method method, Class<?> targetClass, InvocationCallback invocation) throws Throwable{
        TransactionAttributeSource tas = getTransactionAttributeSource();
        TransactionAttribute attr = (tas!=null? tas.getTransactionAttribute(method, targetClass):null);

        PlatformTransactionManager manager = getTransactionManager();
        String joinPointIdentification = methodIdentification(method, targetClass);
        TransactionInfo info = createTransactionIfNecessary(manager, attr, joinPointIdentification);

        Object retVal = null;
        try {
            retVal = invocation.proceedWithInvocation();
        }catch (Throwable e){
            completeTransactionAfterThrowing(info, e);
            throw e;
        }finally {
            cleanupTransactionInfo(info);
        }
        commitTransactionAfterReturning(info);

        return retVal;
    }

    private String methodIdentification(Method method, Class<?> targetClass){
        return ClassUtils.getQualifiedMethodName(method, targetClass);
    }

    protected TransactionInfo createTransactionIfNecessary(PlatformTransactionManager tm, TransactionAttribute attr, String joinPointIdentification){
        if (attr!=null && attr.getName()==null){
            attr = new DelegatingTransactionAttribute(attr) {
                @Override
                public String getName() {
                    return joinPointIdentification;
                }
            };
        }
        TransactionStatus status = null;
        if (attr!=null && tm!=null){
            status = tm.getTransaction(attr);
        }

        return prepareTransactionInfo(tm, attr, joinPointIdentification, status);
    }

    protected TransactionInfo prepareTransactionInfo(PlatformTransactionManager tm, TransactionAttribute attr, String joinPointIdentification, TransactionStatus status) {
        TransactionInfo info = new TransactionInfo(tm, attr, joinPointIdentification);
        if (attr != null){
            info.setTransactionStatus(status);
        }
        info.bindToThread();
        return info;
    }

    protected void completeTransactionAfterThrowing(TransactionInfo info, Throwable ex){
        if (info!=null && info.getTransactionStatus()!=null){
            if (info.transactionAttribute!=null && info.transactionAttribute.rollbackOn(ex)){
                info.getTransactionManager().rollback(info.getTransactionStatus());
            }else {
                info.getTransactionManager().commit(info.getTransactionStatus());
            }
        }
    }

    protected void cleanupTransactionInfo(TransactionInfo info){
        if (info != null){
            info.restoreThreadLocalStatus();
        }
    }

    protected void commitTransactionAfterReturning(TransactionInfo info){
        if (info!=null && info.getTransactionStatus()!=null){
            info.getTransactionManager().commit(info.getTransactionStatus());
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    protected interface InvocationCallback{
        Object proceedWithInvocation() throws Throwable;
    }

    public TransactionAttributeSource getTransactionAttributeSource() {
        return transactionAttributeSource;
    }

    public void setTransactionAttributeSource(TransactionAttributeSource transactionAttributeSource) {
        this.transactionAttributeSource = transactionAttributeSource;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    protected final class TransactionInfo{
        private final PlatformTransactionManager transactionManager;
        private final TransactionAttribute transactionAttribute;
        private final String joinPointIdentification;
        private TransactionStatus transactionStatus;
        private TransactionInfo transactionInfo;

        public TransactionInfo(PlatformTransactionManager transactionManager, TransactionAttribute transactionAttribute, String joinPointIdentification) {
            this.transactionManager = transactionManager;
            this.transactionAttribute = transactionAttribute;
            this.joinPointIdentification = joinPointIdentification;
        }

        public PlatformTransactionManager getTransactionManager() {
            return transactionManager;
        }

        public String getJoinPointIdentification() {
            return joinPointIdentification;
        }

        public TransactionStatus getTransactionStatus() {
            return transactionStatus;
        }

        public void setTransactionStatus(TransactionStatus transactionStatus) {
            this.transactionStatus = transactionStatus;
        }

        public boolean hasTransaction(){
            return this.transactionStatus != null;
        }

        private void bindToThread(){
            this.transactionInfo = transactionInfoHolder.get();
            transactionInfoHolder.set(this);
        }

        private void restoreThreadLocalStatus(){
            transactionInfoHolder.set(this.transactionInfo);
        }

    }

}
