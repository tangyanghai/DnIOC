package com.example.administrator.dnioc.ioc;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Administrator
 * @time : 17:39
 * @for : 事件注入
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@InjectEventParent(
        listenerName = "setOnClickListener",
        listenerType = View.OnClickListener.class,
        listenerMethod = "onClick"
)
public @interface OnViewClick {
    int[] value();
}
