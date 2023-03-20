package com.framework.core.convert.converter;

/**
 * 注册转换器、转换器工厂
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

}
