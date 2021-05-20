package com.app.jetp2.mvp.ui;

import android.os.Bundle;

import com.app.jetp2.R;
import com.app.jetp2.base.BaseActivity;
import com.app.jetp2.bo.ImageBo;
import com.app.jetp2.bo.InfosBo;
import com.app.jetp2.mvp.contract.MainContract;
import com.app.jetp2.mvp.presenter.MainPresenter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author:create by ys
 * 时间:2021/5/18 17
 * 邮箱 894417048@qq.com
 */
public class MainActivity extends BaseActivity<MainContract.MainView,MainContract.IMainPresenter> implements MainContract.MainView {

    @BindView(R.id.image_list)
    RecyclerView imageList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.IMainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public MainContract.MainView createView() {
        return this;
    }

    @Override
    public void init() {
        getPresenter().getImages();
    }


    @Override
    public void getImages(List<ImageBo> imageBos) {

    }

    @Override
    public void getWangYiNews(List<InfosBo> infosBos) {

    }
}