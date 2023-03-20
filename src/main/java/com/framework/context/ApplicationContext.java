package com.framework.context;

import com.framework.beans.factory.ListableBeanFactory;
import com.framework.core.resource.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
