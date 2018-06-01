package com.example.administrator.dnioc.ioc;

import android.content.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
     *
     */
    private static void injectEvent(Context context) {
        Class<?> clz = context.getClass();
        Method[] methods = clz.getMethods();
        if (methods == null) {
            return;
        }

        for (Method method : methods) {
            //获取事件注入注解
            InjectEventParent annotation = method.getAnnotation(InjectEventParent.class);
            if (annotation == null) {
                continue;
            }

            //获取事件三要素
            String name = annotation.listenerName();
            Class<?> aClass = annotation.listenerType();
            String methodName = annotation.listenerMethod();

            try {
                Method value = annotation.getClass().getDeclaredMethod("value");
                int[] ids = (int[])value.invoke(annotation);


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
     * View 注入
     */
    private static void injectView(Context context) {
        Class<?> clz = context.getClass();
        Field[] fields = clz.getDeclaredFields();
        if (fields == null) {
            return;
        }
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations == null) {
                continue;
            }
            for (Annotation annotation : annotations) {
                //获取到View注入注解
                if (annotation instanceof InjectView) {
                    //拿到id
                    int id = ((InjectView) annotation).value();
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
        }
    }

    /**
     * 布局注入
     */
    private static void injectCententLayout(Context context) {
        Class<?> clz = context.getClass();
        Annotation[] annotations = clz.getAnnotations();
        if (annotations == null) {
            return;
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof InjectLayout) {
                //布局Id
                int layoutId = ((InjectLayout) annotation).value();
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
                return;
            }
        }
    }


}
