package com.cyx.sms_monitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/11/16.
 */
public class MyDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popuplayout);


        Window window = getWindow();

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
       /* layoutParams.width = 200;
        layoutParams.height = 200;*/
        layoutParams.alpha = 1.0f;
        window.setAttributes(layoutParams);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyDialogActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
