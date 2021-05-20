package com.app.jetp2.mvp.listener;

import com.app.jetp2.bo.ImageBo;
import com.app.jetp2.bo.InfosBo;

import java.util.List;

/**
 * @author:create by ys
 * 时间:2021/5/18 16
 * 邮箱 894417048@qq.com
 */
public interface MainCompleteListener {

    void getImages(List<ImageBo> imageBos);

    void getWangYiNews(List<InfosBo> infosBos);

}
