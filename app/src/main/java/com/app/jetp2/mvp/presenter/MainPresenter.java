package com.app.jetp2.mvp.presenter;

import android.content.Context;

import com.app.jetp2.bo.ImageBo;
import com.app.jetp2.bo.InfosBo;
import com.app.jetp2.mvp.contract.MainContract;
import com.app.jetp2.mvp.listener.MainCompleteListener;
import com.app.jetp2.mvp.model.MainModel;

import java.util.List;

/**
 * @author:create by ys
 * 时间:2021/5/18 11
 * 邮箱 894417048@qq.com
 */
public class MainPresenter extends MainContract.IMainPresenter implements MainCompleteListener {

    private MainModel mainModel;
    private Context context;

    public MainPresenter(Context context){
        this.context = context;
        this.mainModel = new MainModel();
    }

    @Override
    public void getImages() {
        if (getView() != null){
            mainModel.getImages(this,context);
        }
    }

    @Override
    public void getWangYiNews() {
        if (getView() != null){
            mainModel.getWangYiNews(this,context);
        }
    }


    @Override
    public void getImages(List<ImageBo> imageBos) {
        if (imageBos != null && imageBos.size() > 0){
            for (ImageBo imageBo : imageBos){
                System.out.println("MAIN_IMAGE_URL: " + imageBo.getImg());
            }
        }
    }

    @Override
    public void getWangYiNews(List<InfosBo> infosBos) {
        if (infosBos != null && infosBos.size() > 0){
            for (InfosBo infosBo : infosBos){
                System.out.println("MAIN_TITLE: " + infosBo.getTitle());
            }
        }
    }
}