package com.example.administrator.dnioc.ioc;

import android.view.View;

/**
 * @author : Administrator
 * @time : 17:22
 * @for :
 */
public @interface InjectEventParent {
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
