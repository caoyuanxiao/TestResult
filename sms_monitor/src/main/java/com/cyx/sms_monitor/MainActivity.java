package com.cyx.sms_monitor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cyx.sms_monitor.Observe.SMS_Observer;

public class MainActivity extends Activity {

    SMS_Observer mSMS_Observer;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    System.out.println("信息來了");
                    ShowPoPuwind();
                    break;
            }
        }

        private void ShowPoPuwind() {
            System.out.println("顯示popuwind");
            View contentView = LayoutInflater.from(mWindow.getContext()).inflate(R.layout.popuplayout, null);
            PopupWindow mPopWindow = new PopupWindow(contentView,
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
            mPopWindow.setContentView(contentView);
            //设置各个控件的点击响应
            final TextView tv1 = (TextView) contentView.findViewById(R.id.tv);
            tv1.setText("111111");

            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("点击了信息");
                }
            });
            //显示PopupWindow
            View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
            mPopWindow.showAtLocation( mWindow.getDecorView(), Gravity.CENTER, 0, 0);


        }
    };

    Window mWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWindow = getWindow();




        /*Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);*/

    }

    @Override
    protected void onResume() {
        super.onResume();//为content://sms的数据改变注册监听器
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                return;
            } else {
                //上面已经写好的监听短信方法
                getContentResolver().registerContentObserver(Uri.parse
                        ("content://sms"), true, new SMS_Observer(mHandler, this));
            }
        } else {
            //上面已经写好的监听短信方法
            getContentResolver().registerContentObserver(Uri.parse
                    ("content://sms"), true, new SMS_Observer(new Handler(), this));
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    getContentResolver().registerContentObserver(Uri.parse
                            ("content://sms"), true, new SMS_Observer(new Handler(), this));
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


}
