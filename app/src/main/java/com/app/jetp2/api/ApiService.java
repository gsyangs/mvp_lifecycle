package com.app.jetp2.api;

import com.app.jetp2.base.BaseResponse;
import com.app.jetp2.bo.ImageBo;
import com.app.jetp2.bo.InfosBo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author:create by ys
 * 时间:2021/5/18 14
 * 邮箱 894417048@qq.com
 */
public interface ApiService {

    /**
     * 获取图片资源
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("getImages")
    Observable<BaseResponse<List<ImageBo>>> getImages(@FieldMap Map<String,Object> params);

    /**
     * 获取咨询列表
     */
    @FormUrlEncoded
    @POST("getWangYiNews")
    Observable<BaseResponse<List<InfosBo>>> getWangYiNews(@FieldMap Map<String,Object> params);
}
