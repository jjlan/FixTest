package com.ljj.patch.util;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

/**
 * Created by ljj on 2017/11/28.
 */

public class HotFix {
  public static void init(Context context) {
    File hackDir = context.getDir("hackDir", 0);
    File hackJar = new File(hackDir,"hack.apk");
    try {
      AssetUtils.copyAssets(context,"hack.apk", hackJar.getAbsolutePath());
      DexUtils.inject(context,hackJar.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void inject(Context context,String dexPath){

    File file=new File(dexPath);
    if(file.exists()){
      Log.i("ljj", "inject: ");
      DexUtils.inject(context,dexPath);
    }
  }

}
