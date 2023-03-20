package com.framework.core;

import com.framework.core.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Objects;

public class MethodClassKey implements Comparable<MethodClassKey>{

    private final Method method;
    private final Class<?> targetClass;

    public MethodClassKey(Method method, Class<?> targetClass) {
        this.method = method;
        this.targetClass = targetClass;
    }

    @Override
    public int compareTo(MethodClassKey other) {
        int result = this.method.getName().compareTo(other.method.getName());
        if (result == 0){
            result = this.method.toString().compareTo(other.method.toString());
            if (result==0 && this.targetClass!=null && other.targetClass!=null){
                result = this.targetClass.getName().compareTo(other.targetClass.getName());
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MethodClassKey)) return false;
        MethodClassKey otherKey = (MethodClassKey) o;
        return this.method.equals(otherKey.method) && ObjectUtils.nullSafeEquals(this.targetClass, otherKey.targetClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, targetClass);
    }

    @Override
    public String toString() {
        return "MethodClassKey{" +
                "method=" + method +
                ", targetClass=" + targetClass +
                '}';
    }
}
