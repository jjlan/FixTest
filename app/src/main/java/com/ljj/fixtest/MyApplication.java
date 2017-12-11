package com.ljj.fixtest;

import java.io.File;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.ljj.patch.util.HotFix;

/**
 * Created by ljj on 2017/11/21.
 */

public class MyApplication extends Application{

  @Override
  public void onCreate() {
    super.onCreate();
    HotFix.init(this);
    String dexPath= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"patch.dex";
    Log.i("ljj", "onCreate: "+Environment.getExternalStorageDirectory().getAbsolutePath());
     HotFix.inject(this,dexPath);
  }
}
