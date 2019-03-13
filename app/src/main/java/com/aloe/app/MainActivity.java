package com.aloe.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private View view;
  private View.OnClickListener listener = ClickProxy.getInstance().create(v -> {
    if (v.getId() == R.id.tv) {
      Log.i("aloe", "处理点击逻辑");
    }
  });

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    view = getWindow().getDecorView();
    TextView tv = findViewById(R.id.tv);
    tv.setTag(R.id.tag, "主页点击统计");
    tv.setOnClickListener(listener);
  }

  @Override
  protected void onStart() {
    super.onStart();
    view.setTag(R.id.tag, "进入主页");
    listener.onClick(view);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    view.setTag(R.id.tag, "离开主页");
    listener.onClick(view);
  }
}
