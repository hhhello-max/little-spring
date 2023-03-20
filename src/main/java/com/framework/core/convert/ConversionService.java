package com.framework.core.convert;

/**
 * 类型转换接口
 */
public interface ConversionService {

    boolean canConvert(Class<?> source, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);

}
