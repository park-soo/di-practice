package org.example.di;


import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BeanFactoryTest {


    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach // test 메서드가 호출되기 전에 미리 호출되는 메서드
    void setUp() {
        reflections = new Reflections("org.example");
        // 만들지 않은 메서드를 미리 정의하고 코드를 작성하는 방식이 top down 방식 이라고 함.
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
        // UserController, UserService 가 return
        beanFactory = new BeanFactory(preInstantiatedClazz);

    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) { //Annotation 타입의 객체가 여러개 들어올 수 있다는 의미
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}