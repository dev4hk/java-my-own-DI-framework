package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    public static Constructor<?> getInjectedConstructor(Class<?> beanClass) {
        Set<Constructor> constructors = ReflectionUtils.getAllConstructors(beanClass, ReflectionUtils.withAnnotation(Inject.class));
        if(constructors.isEmpty()) {
            return null;
        }
        return constructors.iterator().next();
    }
}
