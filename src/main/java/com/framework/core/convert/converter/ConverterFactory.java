package com.framework.core.convert.converter;

public interface ConverterFactory<S, R> {

    /**
     * 获取转换器，可从类型S转为T，T是R的子类
     * @param targetType
     * @return
     * @param <T>
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
