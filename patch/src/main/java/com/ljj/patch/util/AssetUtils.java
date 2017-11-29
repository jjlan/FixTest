package com.ljj.patch.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

/**
 * Created by ljj on 2017/11/28.
 */

public class AssetUtils {
  public static void copyAssets(Context context, String assetsName, String destFilePath) throws IOException {
    File file = new File(destFilePath);
    FileOutputStream out =  new FileOutputStream(file);

    InputStream in = context.getAssets().open(assetsName);

    byte[] buf = new byte[1024];
    int len;
    while ((len= in.read(buf)) != -1) {
      out.write(buf,0,len);
    }
    in.close();
    out.close();
  }
}
