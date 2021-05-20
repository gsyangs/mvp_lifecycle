package com.app.jetp2.base;

import android.app.Activity;
import android.content.Context;


import com.app.jetp2.observer.ObserverResponseListener;
import com.app.jetp2.observer.ProgressObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
 * M层父类
 */

public class BaseModel<T> {
    /**
     * 封装线程管理和订阅的过程
     * flag  是否添加progressdialog
     */
    public void subscribe(Context context, final Observable observable, ObserverResponseListener<T> listener, boolean isDialog, boolean cancelable) {
        Observer<T> observer = new ProgressObserver(context, listener, isDialog,cancelable);
        //绑定生命周期
        ((AppCompatActivity)context).getLifecycle().addObserver((LifecycleObserver) observer);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
