package com.example.administrator.dnioc.ioc;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Administrator
 * @time : 17:22
 * @for :
 */
@Retention(RetentionPolicy.RUNTIME)
//该注解在另外一个注解上使用
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {
    /**
     * @return 要设置的事件的名称
     */
    String listenerName();

    /**
     * @return 要设置事件的类型
     */
    Class<?> listenerType();

    /**
     * @return 事件触发后,执行的方法的名称
     */
    String listenerMethod();



}
