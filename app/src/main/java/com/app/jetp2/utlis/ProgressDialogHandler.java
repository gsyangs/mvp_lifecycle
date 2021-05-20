package com.app.jetp2.utlis;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.app.jetp2.common.LoadingDialog;

/**
 * Dialog的进度控制
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private LoadingDialog sad;
    private Context context;
    private boolean cancelable;

    public ProgressDialogHandler(Context context , boolean cancelable) {
        super();
        this.context = context;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
        if (sad == null) {
            sad = new LoadingDialog(context,cancelable);
            if (!sad.isShowing()) {
                sad.show();
            }
        }else{
            sad.show();
        }
    }

    private void dismissProgressDialog() {
        if (sad != null) {
            sad.dismiss();
            sad = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
