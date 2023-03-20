package com.framework.transaction.annotation;

import com.framework.core.annotation.AnnotatedElementUtil;
import com.framework.core.annotation.AnnotationAttributes;
import com.framework.transaction.interceptor.RollbackRuleAttribute;
import com.framework.transaction.interceptor.RuleBasedTransactionAttribute;
import com.framework.transaction.interceptor.TransactionAttribute;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

public class SpringTransactionAnnotationParser implements TransactionAnnotationParser, Serializable {
    @Override
    public TransactionAttribute parseTransactionAnnotation(AnnotatedElement element) {
        AnnotationAttributes attributes = AnnotatedElementUtil.findMergedAnnotationAttribute(element, Transactional.class, false, false);
        if (attributes != null){
            return parseTransactionAnnotation(attributes);
        }

        return null;
    }


    protected TransactionAttribute parseTransactionAnnotation(AnnotationAttributes attributes){
        RuleBasedTransactionAttribute attribute = new RuleBasedTransactionAttribute();

        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        for (Class<?> rule : attributes.getClassArray("rollbackFor")){
            rollbackRules.add(new RollbackRuleAttribute(rule));
        }

        attribute.setRollbackRules(rollbackRules);
        return attribute;
    }

}
