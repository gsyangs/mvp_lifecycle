package com.app.jetp2.mvp.contract;

import android.view.View;

import com.app.jetp2.base.BasePresenter;
import com.app.jetp2.base.BaseView;
import com.app.jetp2.bo.ImageBo;
import com.app.jetp2.bo.InfosBo;

import java.util.List;

/**
 * @author:create by ys
 * 时间:2021/5/18 16
 * 邮箱 894417048@qq.com
 */
public interface MainContract {

    interface MainView extends BaseView {

        void getImages(List<ImageBo> imageBos);

        void getWangYiNews(List<InfosBo> infosBos);
    }

    abstract class IMainPresenter extends BasePresenter<MainView> {

        public abstract void getImages();

        public abstract void getWangYiNews();
    }

} 