package com.example.test_gugulive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.test_gugulive.Bean.Person;
import com.example.test_gugulive.Event.FristEvent;
import com.example.test_gugulive.R;
import com.example.test_gugulive.Test_ResultActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity1";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        EventBus.getDefault().register(this);


        String url = getIntent().getStringExtra("url");
        Person person = (Person) getIntent().getSerializableExtra("person");

        Log.i(TAG, "我收到的url为：" + url + "person:" + person.toString());


        findViewById(R.id.btn_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventBus.getDefault().post(new FristEvent("hello world"));

                setResult(Test_ResultActivity.ResultSecondOk, new Intent().putExtra("url", "www.second"));
                finish();
            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        Log.i(TAG, "执行了finish()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "执行了OnDestroy");

        EventBus.getDefault().unregister(this);
    }
}
