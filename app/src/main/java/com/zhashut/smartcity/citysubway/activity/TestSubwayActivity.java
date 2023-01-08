package com.zhashut.smartcity.citysubway.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.citysubway.constant.Constant;
import com.zhashut.smartcity.citysubway.entity.FeedbackList;
import com.zhashut.smartcity.common.ReqCallback;

public class TestSubwayActivity extends AppCompatActivity implements View.OnClickListener {


    private FeedbackList feedbackList;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            feedbackList = (FeedbackList) msg.obj;
            System.out.println("handleMessage："+feedbackList);
            System.out.println(feedbackList.code);//401
            System.out.println(feedbackList.rows);
            System.out.println("feedbackList.code.equals(\"200\") = " + feedbackList.code.equals("200"));
            if (feedbackList.code.equals("200")){
                feedbackSuccess(feedbackList);
            }
        }
    };

    private void feedbackSuccess(FeedbackList feedbackList) {
        Intent intent = new Intent(this, ListFeedbackActivity.class);
        intent.putExtra("listFields",feedbackList);
        System.out.println("feedbackSuccess"+feedbackList);
        startActivity(intent);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_subway);

        findViewById(R.id.btn_go_feedback).setOnClickListener(this);
        findViewById(R.id.btn_go_feedbackList).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go_feedback:
                Intent intent = new Intent(this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_go_feedbackList:
                SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
                String token = preferences.getString("token", "");
                System.out.println(token);
                ReqCallback reqCallback = new ReqCallback();
                reqCallback.CallBack(Constant.FEEDBACK_ALL_URL,token,"",handler,FeedbackList.class);
                break;
        }
    }
}