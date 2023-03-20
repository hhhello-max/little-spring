package com.framework.beans.factory;

import com.framework.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
