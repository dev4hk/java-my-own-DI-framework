package org.example.di;

import org.example.controller.UserController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    private final Set<Class<?>> preInstantiatedClasses;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClasses) {
        this.preInstantiatedClasses = preInstantiatedClasses;
    }

    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }
}
