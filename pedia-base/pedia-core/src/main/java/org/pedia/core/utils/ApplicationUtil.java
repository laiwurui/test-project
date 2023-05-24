package org.pedia.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class ApplicationUtil implements ApplicationContextAware, EnvironmentAware {

    private ApplicationContext applicationContext;

    private Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public <T> T getObject(String name, Class<T> T) {
        return applicationContext.getBean(name, T);
    }

    public Object getObject(String name) {
        return applicationContext.getBean(name);
    }

    public String getApplicationName() {
        return applicationContext.getApplicationName();
    }

    public String getProperty(String property) {
        return environment.getProperty(property);
    }
}