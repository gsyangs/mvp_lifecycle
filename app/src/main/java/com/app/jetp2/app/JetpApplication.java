package com.app.jetp2.app;

import android.app.Application;

/**
 * Created by smile on 2017/2/27.
 */
public class JetpApplication extends Application {

    private static JetpApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        JetpApplication.instance = this;
    }

    public static JetpApplication getInstance() {
        return JetpApplication.instance;
    }

}
