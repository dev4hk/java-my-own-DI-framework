package org.example.di;

import org.example.annotation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {
    private final Set<Class<?>> preInstantiatedClasses;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClasses) {
        this.preInstantiatedClasses = preInstantiatedClasses;
        initialize();
    }

    private void initialize() {
        for (Class<?> beanClass : preInstantiatedClasses) {
            Object instance = createInstance(beanClass);
            beans.put(beanClass, instance);
        }
    }

    private Object createInstance(Class<?> beanClass) {
        Constructor<?> constructor = findConstructor(beanClass);
        List<Object> parameters = new ArrayList<>();
        for(Class<?> typeClass : constructor.getParameterTypes()){
            parameters.add(getParameterByClass(typeClass));
        }
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> beanClass) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(beanClass);
        return Objects.nonNull(constructor) ? constructor : beanClass.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object bean = getBean(typeClass);
        return Objects.nonNull(bean) ? bean : createInstance(typeClass);
    }

    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }
}
