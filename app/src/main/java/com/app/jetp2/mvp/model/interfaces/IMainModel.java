package com.app.jetp2.mvp.model.interfaces;

import android.content.Context;

import com.app.jetp2.mvp.listener.MainCompleteListener;

/**
 * @author:create by ys
 * 时间:2021/5/18 16
 * 邮箱 894417048@qq.com
 */
public interface IMainModel<T> {

    void getImages(MainCompleteListener listener, Context context);

    void getWangYiNews(MainCompleteListener listener, Context context);

}
