package com.cyx.sms_monitor.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cyx.sms_monitor.Service.MySMS_Service;

/**
 * Created by Administrator on 2016/11/17.
 */
public class Sms_BroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("短信来了");

        Intent intent1 = new Intent(context, MySMS_Service.class);
        context.startService(intent1);

    }


}
