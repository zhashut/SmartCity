package com.zhashut.smartcity.park.activity;

import static com.zhashut.smartcity.park.constant.constant.CORRECT_URL;
import static com.zhashut.smartcity.park.constant.constant.FEEDBACK_LIST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.common.ReqResult;
import com.zhashut.smartcity.common.ResultEntity;
import com.zhashut.smartcity.park.entity.FeedbackList;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_title;
    private EditText et_content;
    private SharedPreferences preferences;
    ReqCallback callback = new ReqCallback();
    private String token;
    private String successCode = "200";

    /**
     * 添加意见反馈接口回调
     */
    private Handler addFeedbackHandler = ReqResult.ResultHandler(this, ParkHomeActivity.class);

    /**
     * 查询意见反馈接口回调
     */
    private Handler feedbackListHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            FeedbackList feedbackList = (FeedbackList) msg.obj;
            if (feedbackList.code.equals(successCode)) {
                feedbackListSuccess(feedbackList);
            }
        }
    };

    /**
     * 查询意见反馈列表成功
     */
    private void feedbackListSuccess(FeedbackList feedbackList) {
        Intent intent = new Intent(this, FeedbackListActivity.class);
        intent.putExtra("feedbackList", feedbackList);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        token = preferences.getString("token", "");
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("意见反馈");
        TextView tv_other = findViewById(R.id.tv_other);
        tv_other.setText("反馈记录");
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        findViewById(R.id.btn_commit).setOnClickListener(this);
        tv_other.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                String title = et_title.getText().toString().trim();
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    Toast.makeText(this, "请将信息完整填写", Toast.LENGTH_SHORT).show();
                    return;
                }
                AddFeedbackLoading(token, title, content);
                break;
            case R.id.tv_other:
                callback.CallBackWithToken(FEEDBACK_LIST, token, feedbackListHandler, FeedbackList.class);
                break;
        }
    }

    /**
     * 请求等待添加意见反馈接口
     */
    private void AddFeedbackLoading(String token, String title, String content) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("content", content);
            // 回调方法
            callback.CallBack(CORRECT_URL, token, jsonObject.toString(), addFeedbackHandler, ResultEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}