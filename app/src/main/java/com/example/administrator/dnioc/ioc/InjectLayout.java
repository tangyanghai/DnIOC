package com.example.administrator.dnioc.ioc;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Administrator
 * @time : 14:36
 * @for : 布局注入注解,实现activity的布局注入
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectLayout {

    /**
     * @return 布局Id
     */
    @LayoutRes int value();
}
