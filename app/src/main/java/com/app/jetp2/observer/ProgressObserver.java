package com.app.jetp2.observer;

import android.content.Context;
import android.util.Log;


import com.app.jetp2.base.BaseResponse;
import com.app.jetp2.utlis.ExceptionHandle;
import com.app.jetp2.utlis.ProgressDialogHandler;
import com.app.jetp2.utlis.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 观察者
 */
public class ProgressObserver<T> implements Observer<T> ,BaseLifecycleObserver{
    private static final String TAG = "ProgressObserver____ ";
    private ObserverResponseListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private Disposable d;
    private int SUCCESS_CODE = 200;

    public ProgressObserver(Context context, ObserverResponseListener listener, boolean isDialog, boolean cancelable) {
        this.listener = listener;
        this.context = context;
        if(isDialog){
            mProgressDialogHandler = new ProgressDialogHandler(context, cancelable);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        Log.e(TAG, "onSubscribe: ");
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (t instanceof BaseResponse){
            BaseResponse response = (BaseResponse) t;
            if (SUCCESS_CODE == response.getCode()){
                listener.onNext(response.getResult());
            }else{
                ToastUtil.showLongToast(response.getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        Log.e(TAG, "onError: ", e);
        //自定义异常处理
        if(e instanceof ExceptionHandle.ResponeThrowable){
            listener.onError((ExceptionHandle.ResponeThrowable)e);
        } else {
            listener.onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }

        if (e instanceof UnknownHostException) {
            ToastUtil.showLongToast("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showLongToast("请求超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.showLongToast("连接失败");
        } else if (e instanceof HttpException) {
            ToastUtil.showLongToast("请求超时");
        }else {
            ToastUtil.showLongToast("请求失败");
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.e(TAG, "onComplete: ");
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    //绑定activity生命周期
    //当activity销毁是 事物终止
    @Override
    public void onDestroy() {
        System.out.println("MMAIN_结束Rxjava事物");
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
