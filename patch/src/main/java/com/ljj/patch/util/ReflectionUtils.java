package com.ljj.patch.util;

import java.lang.reflect.Field;

/**
 * Created by ljj on 2017/11/21.
 */

public class ReflectionUtils {

  public static Object getField(Class<?> cl, Object obj, String field) throws NoSuchFieldException,
      IllegalAccessException {
    Field localField = cl.getDeclaredField(field);
    localField.setAccessible(true);
    return localField.get(obj);
  }

  public static void setFeild(Class<?> cl, Object obj, String field, Object value) throws NoSuchFieldException,
      IllegalAccessException {
    Field localField = cl.getDeclaredField(field);
    localField.setAccessible(true);
    localField.set(obj, value);
  }

}
