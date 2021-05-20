package com.app.jetp2.utlis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.app.jetp2.app.JetpApplication;

/**
 * 作者：senon on 2017/12/27 12:25
 * 邮箱：a1083911695@163.com
 */
public class NetworkUtil {

    /**
     * 网络是否连接判断工具
     */
    public static boolean isNetConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) JetpApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;

    }


    public static String getMachineIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            return intToIp(ipAddress);
        }
        return "0.0.0.0";
    }

    private static String intToIp(int ipAddress) {
        return (ipAddress & 0xFF) + "." + ((ipAddress >> 8) & 0xFF) + "." + ((ipAddress >> 16) & 0xFF)
                + "." + (ipAddress >> 24 & 0xFF);
    }
}
