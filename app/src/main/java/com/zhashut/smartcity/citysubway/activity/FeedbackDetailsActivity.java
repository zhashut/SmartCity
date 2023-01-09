package com.zhashut.smartcity.citysubway.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.citysubway.constant.Constant;
import com.zhashut.smartcity.citysubway.entity.Feedback;
import com.zhashut.smartcity.citysubway.entity.FeedbackList;
import com.zhashut.smartcity.common.ReqCallback;

public class FeedbackDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ReqCallback reqCallback = new ReqCallback();

    private FeedbackList feedbackRes;

    private Feedback feedback;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            feedbackRes = (FeedbackList) msg.obj;
            System.out.println("handler");
            if ("200".equals(feedbackRes.code)){
                feedbackSuccess(feedbackRes);
            }
        }
    };

    private TextView title;
    private TextView content;



    private Integer id;
    private SharedPreferences preferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeback_details);
        initView();
        onResult();
    }
    private void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("意见反馈详细信息");

        preferences = getSharedPreferences("config", MODE_PRIVATE);
        token = preferences.getString("token", "");

        title = findViewById(R.id.tv_feed_title);
        content = findViewById(R.id.tv_content);

        findViewById(R.id.iv_back).setOnClickListener(this);

    }

    private void onResult() {
        reqCallback.CallBackByIDWithToken(Constant.FEEDBACK_DETAIL,id,token,handler,FeedbackList.class);
    }

    private void feedbackSuccess(FeedbackList f) {
        feedback = f.data;
        title.setText(feedback.title);
        content.setText(feedback.content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}