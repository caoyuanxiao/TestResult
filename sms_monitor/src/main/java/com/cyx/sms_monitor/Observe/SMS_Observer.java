package com.cyx.sms_monitor.Observe;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cyx.sms_monitor.MainActivity;
import com.cyx.sms_monitor.MyDialogActivity;
import com.cyx.sms_monitor.R;

/**
 * Created by Administrator on 2016/11/16.
 */
public class SMS_Observer extends ContentObserver {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    Context mContext;
    Handler handler;

    public SMS_Observer(Handler handler, Context context) {
        super(handler);
        this.handler = handler;
        this.mContext = context;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);

        if (uri.toString().equals("content://sms/raw")) {
            return;
        }

        Cursor cursor = mContext.getContentResolver().query(
                Uri.parse("content://sms/inbox"), null, null, null, "date desc");

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndex("body"));
                System.out.println("发件人：" + address + "内容为：" + body);
                Toast.makeText(mContext, "发件人：" + address + "内容为：" + body, 0).show();
               /* Intent intent=new Intent(mContext, MyDialogActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);*/
                //ShowPopuWind(body);
                handler.sendEmptyMessage(1);
            }
            cursor.close();
        }
    }

    private void ShowPopuWind(String body) {

        System.out.println("顯示popuwind");
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popuplayout, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        final TextView tv1 = (TextView) contentView.findViewById(R.id.tv);
        tv1.setText(body);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("点击了信息");
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }


}
