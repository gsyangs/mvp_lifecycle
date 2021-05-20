package com.app.jetp2.base;

import com.app.jetp2.observer.BaseLifecycleObserver;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author:create by ys
 * 时间:2021/5/18 11
 * 邮箱 894417048@qq.com
 */
public class BasePresenter<V extends BaseView> implements BaseLifecycleObserver {

    private V mView;

    public V getView(){
        return mView;
    }

    public void attachView(V v){
        mView = v;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        System.out.println("MMAIN_移除view");
        mView = null;
    }
}
