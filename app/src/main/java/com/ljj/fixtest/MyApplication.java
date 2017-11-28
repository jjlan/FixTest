package com.ljj.fixtest;

import android.app.Application;

import com.ljj.patch.util.DexUtils;

/**
 * Created by ljj on 2017/11/21.
 */

public class MyApplication extends Application{

  @Override
  public void onCreate() {
    super.onCreate();
    DexUtils.inject(this);
  }
}
