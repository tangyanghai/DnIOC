package com.example.administrator.dnioc.ioc;

import android.content.Context;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : Administrator
 * @time : 14:34
 * @for : 注解工具类
 */
public class InjectUtils {
    public static void inject(Context context) {
        //布局注入
        injectCententLayout(context);
        //View注入
        injectView(context);
        //事件事件
        injectEvent(context);
    }

    /**
     * 事件注入
     * 事件注入针对的是方法
     */
    private static void injectEvent(Context context) {
        Class<?> clz = context.getClass();
        Method[] methods = clz.getDeclaredMethods();
        if (methods == null) {
            return;
        }

        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            if (annotations == null) {
                continue;
            }

            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }
                //获取事件三要素
                String name = eventBase.listenerName();
                Class<?> listenerType = eventBase.listenerType();
                String methodName = eventBase.listenerMethod();

                try {
                    //获取id-->通过反射拿
                    Method valueMethod = annotation.getClass().getDeclaredMethod("value");
                    int[] ids = (int[]) valueMethod.invoke(annotation);
                    //对每一个id设置事件
                    for (int id : ids) {
                        //拿到view
                        Method findViewById = clz.getMethod("findViewById", int.class);
                        View view = (View) findViewById.invoke(context, id);
                        if (view == null) {
                            return;
                        }
                        //获取代理类
                        ListenerProxy listenerProxy = new ListenerProxy(context, method);
                        Object o = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, listenerProxy);
                        //拿到要设置到view中去的事件的方法
                        Method onClickMethod = view.getClass().getMethod(name, listenerType);
                        onClickMethod.invoke(view, o);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * View 注入
     */
    private static void injectView(Context context) {
        Class<?> clz = context.getClass();
        Field[] fields = clz.getDeclaredFields();
        if (fields == null) {
            return;
        }
        for (Field field : fields) {
            InjectView annotation = field.getAnnotation(InjectView.class);
            if (annotation == null) {
                continue;
            }
            //获取到View注入注解
            //拿到id
            int id = annotation.value();
            try {
                //findViewById
                Method findViewById = clz.getMethod("findViewById", int.class);
                Object view = findViewById.invoke(context, id);
                //设置View
                field.setAccessible(true);
                field.set(context, view);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 布局注入
     */
    private static void injectCententLayout(Context context) {
        Class<?> clz = context.getClass();
        InjectLayout annotation = clz.getAnnotation(InjectLayout.class);
        if (annotation == null) {
            return;
        }
        //布局Id
        int layoutId = annotation.value();
        //将id通过反射设置到布局中去  setContentView
        try {
            Method setContentView = clz.getMethod("setContentView", int.class);
            setContentView.invoke(context, layoutId);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
