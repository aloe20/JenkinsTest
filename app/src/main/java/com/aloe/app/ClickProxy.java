package com.aloe.app;

import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClickProxy implements InvocationHandler {
  private View.OnClickListener listener;
  private static ClickProxy instance;

  public static ClickProxy getInstance() {
    if (instance == null) {
      synchronized (ClickProxy.class) {
        if (instance == null) {
          instance = new ClickProxy();
        }
      }
    }
    return instance;
  }

  public View.OnClickListener create(View.OnClickListener listener) {
    this.listener = listener;
    return (View.OnClickListener) Proxy.newProxyInstance(listener.getClass().getClassLoader(), listener.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Log.e("aloe", "start");
    Object tag = ((View) args[0]).getTag(R.id.tag);
    if (tag != null) {
      Log.e("aloe", "" + tag);
    }
    Object obj = method.invoke(listener, args);
    Log.e("aloe", "end");
    return obj;
  }
}
