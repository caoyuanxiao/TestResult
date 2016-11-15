package com.example.test_gugulive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.test_gugulive.Bean.Person;
import com.example.test_gugulive.activity.SecondActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Test_ResultActivity extends AppCompatActivity {

    private static final int ResultOk = 0;
    public static final int ResultSecondOk = 1;
    private static final String TAG = "Test_ResultActivity1";

    List<MyTest> myTests=new ArrayList<>();
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__result);

        Log.i(TAG,"oncreat");

        person=new Person();
        person.setAge(1);
        person.setName("yuanxiao");



        findViewById(R.id.btn_test_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("person",person);
                intent.putExtra("url", "www.omaigavip.com");
                intent.setClass(Test_ResultActivity.this, SecondActivity.class);
                startActivityForResult(intent, ResultOk);
            }
        });

        EventBus.getDefault().register(this);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data!=null){
            String url = data.getStringExtra("url");
            Log.i(TAG,"requestCode"+requestCode+"resultCode"+resultCode+"url "+url);
        }
        if (requestCode==ResultOk){
            Log.i(TAG,"我获取到了ResultOK的回调resultCode"+resultCode);
        }

        if (resultCode==ResultSecondOk){
            Log.i(TAG,"我获取到了 ResultSecondOK requestCode"+requestCode);
        }
        }


    class MyTest implements Serializable {
        void Test() {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG,"onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}

