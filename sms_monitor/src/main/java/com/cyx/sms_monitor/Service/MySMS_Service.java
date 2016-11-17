package com.cyx.sms_monitor.Service;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.cyx.sms_monitor.Observe.SMS_Observer;

/**
 * Created by Administrator on 2016/11/16.
 */
public class MySMS_Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //上面已经写好的监听短信方法
        getContentResolver().registerContentObserver(Uri.parse
                ("content://sms"), true, new SMS_Observer(new Handler(), this));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
