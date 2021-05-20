package com.app.jetp2.observer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author:create by ys
 * 时间:2021/5/19 15
 * 邮箱 894417048@qq.com
 */
public interface BaseLifecycleObserver extends LifecycleObserver {

    //创建时
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate();

    //开始启动的时候
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart();

    //界面可见时候
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume();

    //界面停止将要切换
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause();

    //界面完全隐藏时候
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop();

    //结束时候
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy();
}
