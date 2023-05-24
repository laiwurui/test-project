package org.pedia.core.service.impl;

import org.pedia.core.service.ICommonService;

import java.lang.reflect.AnnotatedType;
import java.util.Map;

public abstract class AbstractCommonService<T> implements ICommonService<T> {

    @Override
    public Map<String, String> getFields() {
        Class clazz = this.getClass();
        AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
        return null;
    }

}
