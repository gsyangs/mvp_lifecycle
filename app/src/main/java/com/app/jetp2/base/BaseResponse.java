package com.app.jetp2.base;

/**
 * @author:create by ys
 * 时间:2021/5/18 14
 * 邮箱 894417048@qq.com
 */
public class BaseResponse<T> {
    private int code;
    private String message;
    private T result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
} 