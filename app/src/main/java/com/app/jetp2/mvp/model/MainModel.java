package com.app.jetp2.mvp.model;

import android.content.Context;

import com.app.jetp2.api.Api;
import com.app.jetp2.base.BaseModel;
import com.app.jetp2.bo.ImageBo;
import com.app.jetp2.bo.InfosBo;
import com.app.jetp2.mvp.listener.MainCompleteListener;
import com.app.jetp2.mvp.model.interfaces.IMainModel;
import com.app.jetp2.observer.ObserverResponseListener;
import com.app.jetp2.utlis.ExceptionHandle;
import com.app.jetp2.utlis.ToastUtil;

import java.util.HashMap;
import java.util.List;

/**
 * @author:create by ys
 * 时间:2021/5/18 16
 * 邮箱 894417048@qq.com
 */
public class MainModel<T> extends BaseModel<T> implements IMainModel{

    @Override
    public void getImages(MainCompleteListener listener, Context context) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("page","2");
        map.put("count","20");
        ObserverResponseListener observerResponseListener = new ObserverResponseListener<List<ImageBo>>() {
            @Override
            public void onNext(List<ImageBo> imageBos) {
                listener.getImages(imageBos);
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                ToastUtil.showLongToast(e.message);
            }
        };
        subscribe(context, Api.getApiService().getImages(map),observerResponseListener, true, true);
    }

    @Override
    public void getWangYiNews(MainCompleteListener listener, Context context) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("page","1");
        map.put("count","10");

        ObserverResponseListener observerResponseListener = new ObserverResponseListener<List<InfosBo>>() {
            @Override
            public void onNext(List<InfosBo> infosBos) {
                listener.getWangYiNews(infosBos);
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                ToastUtil.showLongToast(e.message);
            }
        };

        subscribe(context, Api.getApiService().getWangYiNews(map),observerResponseListener, true, true);
    }
}