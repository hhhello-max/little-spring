package com.framework.core.resource;

/**
 * 资源加载器
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
