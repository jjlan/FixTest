package com.ljj.patch.util;

import java.io.File;
import java.lang.reflect.Array;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import dalvik.system.DexClassLoader;

/**
 * Created by ljj on 2017/11/21.
 */

public class DexUtils {


  public static void inject(Context context){
    String dexPath= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"patch.dex";
    File file=new File(dexPath);
    if(file.exists()){
      Log.i("ljj", "inject: ");
      inject(dexPath,context);
    }
  }

  public static void inject(String dexPath,Context context){
    try {
      Class<?> cl = Class.forName("dalvik.system.BaseDexClassLoader");
      Object originPathList=ReflectionUtils.getField(cl,DexUtils.class.getClassLoader(),"pathList");
      Object originElements=ReflectionUtils.getField(originPathList.getClass(),originPathList,"dexElements");
      String dexOpt=context.getDir("odex",0).getAbsolutePath();
      DexClassLoader dexClassLoader=new DexClassLoader(dexPath,dexOpt,dexOpt,DexUtils.class.getClassLoader());
      Object pathList=ReflectionUtils.getField(cl,dexClassLoader,"pathList");
      Object elements=ReflectionUtils.getField(pathList.getClass(),pathList,"dexElements");
      Object combineElements=combineArray(elements,originElements);
      ReflectionUtils.setFeild(originPathList.getClass(),originPathList,"dexElements",combineElements);
      Object object= ReflectionUtils.getField(originPathList.getClass(),originPathList,"dexElements");
      Log.i("ljj", "inject->length: "+Array.getLength(object));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  private static Object combineArray(Object firstArray, Object secondArray) {
    Class<?> localClass = firstArray.getClass().getComponentType();
    int firstArrayLength = Array.getLength(firstArray);
    int allLength = firstArrayLength + Array.getLength(secondArray);
    Object result = Array.newInstance(localClass, allLength);
    for (int index= 0; index < allLength; ++index) {
      if (index < firstArrayLength) {
        Array.set(result, index, Array.get(firstArray, index));
      } else {
        Array.set(result, index, Array.get(secondArray, index - firstArrayLength));
      }
    }
    return result;
  }

}
