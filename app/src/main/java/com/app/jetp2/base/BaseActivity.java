package com.app.jetp2.base;

import android.os.Bundle;

import com.app.jetp2.observer.BaseLifecycleObserver;
import com.app.jetp2.observer.ProgressObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import butterknife.ButterKnife;

/**
 * @author:create by ys
 * 时间:2021/5/18 10
 * 邮箱 894417048@qq.com
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends AppCompatActivity implements BaseView{

    //引用V层和P层
    public P presenter;

    private V view;

    public P getPresenter(){
        return presenter;
    }

    //由子类指定具体类型
    public abstract int getLayoutId();
    public abstract P createPresenter();
    public abstract V createView();
    public abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        if (presenter == null) {
            presenter = createPresenter();
        }

        if (view == null) {
            view = createView();
        }

        if (presenter != null && view != null) {
            presenter.attachView(view);
        }
        //presenter 绑定 activity什么周期
        getLifecycle().addObserver(presenter);
        init();
    }

    @Override
    public void showMsg(String msg) {

    }
}