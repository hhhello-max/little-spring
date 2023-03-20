package com.framework.core.convert.converter;

/**
 * 转换器：从类型S转为类型T
 * @param <S>
 * @param <T>
 */
public interface Converter<S, T> {

    T convert(S source);

}
