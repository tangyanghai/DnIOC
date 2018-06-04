package com.example.administrator.dnioc.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : Administrator
 * @time : 11:18
 * @for : 动态代理-->让OnClickListener.onClick等方法,执行代理的方法
 */
public class ListenerProxy implements InvocationHandler{

    Object context;
    Method method;

    public ListenerProxy(Object activity, Method method) {
        this.context = activity;
        this.method = method;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.method.invoke(context,args);
    }
}
