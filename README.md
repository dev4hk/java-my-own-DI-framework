# My Own DI Framework

This project is to mimic the implementation of BeanFactory in Spring, which is under ApplicationContext, to manage beans.

## Features

- Scan classes with custom annotations, create instances of the classes, store the instances in the bean factory

## Tech Stack

- Java
- Gradle
- Reflections
- JUnit

## Lessons Learned

- Reflections API is helpful to find and instantiate classes with annotations by accessing constructor of class

- The instances can be stored in memory (in this project, HashMap) and managed throughout runtime using @Retention(RetentionPolicy.RUNTIME) annotation


