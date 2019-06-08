package com.itg8.viewobserverlibrary;

import android.util.Log;

import com.itg8.viewobserverlibrary.annotation.BindArrayList;
import com.itg8.viewobserverlibrary.annotation.BindBoolean;
import com.itg8.viewobserverlibrary.annotation.BindField;
import com.itg8.viewobserverlibrary.annotation.BindInt;
import com.itg8.viewobserverlibrary.annotation.BindValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ObjectBinder {
    private static final String TAG = "BindObserver";
    public static void bind(final Object target){
        try {
            Class t=target.getClass();
            bindViews(target, t.getDeclaredFields());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /*
     * initiate the view for the annotated public fields
     * @param obj is any class instance with annotations
     * @param fields list of methods in the class with annotation
     * @param rootView is the inflated view from the XML
     */
    private static void bindViews(final Object obj, Field[] fields) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for(final Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            Object instance= null;
            if(field.isAnnotationPresent(BindBoolean.class)) {
                Annotation annotation = field.getAnnotation(BindBoolean.class);
                if (annotation != null) {
                    BindBoolean bindView = (BindBoolean) annotation;
                    Class aClass = type;
                    Log.d(TAG, "bindViews: "+aClass.getName());
                    boolean isVal=bindView.value();
                    Constructor<Boolean> c = type.getConstructor(boolean.class);
                     instance=c.newInstance(isVal);
                    try {
                        field.setAccessible(true);
                        field.set(obj, instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }else if(field.isAnnotationPresent(BindInt.class)){
                Annotation annotation = field.getAnnotation(BindInt.class);
                if (annotation != null) {
                    BindInt bindView = (BindInt) annotation;
                    Class aClass = type;
                    Log.d(TAG, "bindViews: "+aClass.getName());
                    int isVal=bindView.value();
                    Constructor<Integer> c = type.getConstructor(int.class);
                     instance=c.newInstance(isVal);
                }
            }else if(field.isAnnotationPresent(BindField.class)){
                Annotation annotation = field.getAnnotation(BindField.class);
                if (annotation != null) {
                    BindField bindView = (BindField) annotation;
                    Class aClass = type;
                    Log.d(TAG, "bindViews: "+aClass.getName());
                    Constructor c ;
                    if(field.isAnnotationPresent(BindValue.class)){
                        Annotation annotation1=field.getAnnotation(BindValue.class);
                        if(annotation1!=null){
                            BindValue value=(BindValue)annotation1;
                            String valueString=value.value();
                            c=type.getConstructor(String.class);
                            instance=c.newInstance(valueString);
                        }
                    }else {
                        c=type.getConstructor();
                        instance=c.newInstance();
                    }
                }
            }else if(field.isAnnotationPresent(BindArrayList.class)){
                Annotation annotation = field.getAnnotation(BindArrayList.class);
                if (annotation != null) {
                    BindArrayList bindView = (BindArrayList) annotation;
                    Class aClass = type;
                    Log.d(TAG, "bindViews: "+aClass.getName());
                    Constructor<String> c = type.getConstructor();
                    instance=c.newInstance();
                }
            }
            try {
                field.setAccessible(true);
                field.set(obj, instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
