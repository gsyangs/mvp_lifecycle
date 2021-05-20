package com.app.jetp2.api;


import com.app.jetp2.base.BaseApi;

public class Api {
    /**
     * 线上
     */
    public static final String BASE_URL = "https://api.apiopen.top/";
    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi
                .getRetrofit(BASE_URL)
                .create(ApiService.class);
    }
}
