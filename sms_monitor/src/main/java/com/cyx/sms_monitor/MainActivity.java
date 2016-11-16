package com.cyx.sms_monitor;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cyx.sms_monitor.Observe.SMS_Observer;

public class MainActivity extends AppCompatActivity {

    SMS_Observer mSMS_Observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //为content://sms的数据改变注册监听器
        getContentResolver().registerContentObserver(Uri.parse
                ("content://sms"), true, new SMS_Observer(new Handler(), this));
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
