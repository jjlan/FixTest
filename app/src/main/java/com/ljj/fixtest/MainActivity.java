package com.ljj.fixtest;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final TextView textView=(TextView) findViewById(R.id.mytv);
    findViewById(R.id.myBtn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        textView.setText(new Test().showText());
      }
    });

  }
}
