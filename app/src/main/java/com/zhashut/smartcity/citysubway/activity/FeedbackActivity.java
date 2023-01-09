package com.zhashut.smartcity.citysubway.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.citysubway.constant.Constant;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.common.ReqResult;
import com.zhashut.smartcity.common.ResultEntity;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_title;
    private EditText et_content;
    private SharedPreferences preferences;
    private String token;
    private TextView tv_title;
    private Handler handler = ReqResult.ResultHandler(this,TestSubwayActivity.class,"反馈提交成功!");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        token = preferences.getString("token", "");
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("反馈中心");
        et_title =  findViewById(R.id.et_title);
        et_content =  findViewById(R.id.et_content);
        findViewById(R.id.btn_commit).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_commit:
                String title = et_title.getText().toString().trim();
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)){
                    Toast.makeText(this, "请将信息填写完成", Toast.LENGTH_SHORT).show();
                    return;
                }
                feedbackLoading(token,title,content);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void feedbackLoading(String token, String title, String content) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title",title);
            jsonObject.put("content",content);
            ReqCallback reqCallback = new ReqCallback();
            reqCallback.CallBack(Constant.FEEDBACK_ADD_URL,token,jsonObject.toString(),handler, ResultEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}