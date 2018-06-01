package com.example.administrator.dnioc.ioc;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Administrator
 * @time : 16:56
 * @for : View注入注解
 */
@Retention(RetentionPolicy.RUNTIME)//运行时
@Target(ElementType.FIELD)//针对成员变量
public @interface InjectView {
    /**
     * @return View的id
     */
    @IdRes int value();
}
