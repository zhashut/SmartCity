package com.zhashut.smartcity.park.activity;

import static com.zhashut.smartcity.park.constant.constant.CORRECT_URL;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcity.Login.activity.LoginActivity;
import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqResult;
import com.zhashut.smartcity.common.ResultEntity;
import com.zhashut.smartcity.common.ReqCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class CorrectActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;
    private String token;
    private EditText park_name;
    private EditText park_spot;
    private EditText park_content;
    private EditText park_pho;
    private EditText park_remark;
    private Handler handler = ReqResult.ResultHandler(CorrectActivity.this, LoginActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        token = preferences.getString("token", "");
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("信息纠错");
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        park_name = findViewById(R.id.park_name);
        park_spot = findViewById(R.id.park_spot);
        park_content = findViewById(R.id.park_content);
        park_pho = findViewById(R.id.park_pho);
        park_remark = findViewById(R.id.park_remark);
        iv_back.setOnClickListener(this);
        findViewById(R.id.btn_park_commit).setOnClickListener(this);
    }


    /**
     * 控件点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_park_commit:
                String parkName = park_name.getText().toString().trim();
                String parkSpot = park_spot.getText().toString().trim();
                String parkContent = park_content.getText().toString().trim();
                String parkPho = park_pho.getText().toString().trim();
                String parkRemark = park_remark.getText().toString().trim();
                if (TextUtils.isEmpty(parkName) || TextUtils.isEmpty(parkSpot) || TextUtils.isEmpty(parkContent)) {
                    Toast.makeText(this, "请将信息填写完成", Toast.LENGTH_LONG).show();
                    return;
                }
                parkLoading(token, parkName, parkSpot, parkContent, parkPho, parkRemark);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 加载
     *
     * @param token       token
     * @param parkName    停车场名称
     * @param parkSpot    停车位数
     * @param parkContent 问题描述
     * @param parkPho     图片
     * @param parkRemark  备注
     */
    private void parkLoading(String token, String parkName, String parkSpot, String parkContent, String parkPho, String parkRemark) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", parkName);
            jsonObject.put("spotCount", parkSpot);
            jsonObject.put("content", parkContent);
            jsonObject.put("photo", parkPho);
            jsonObject.put("remark", parkRemark);
            ReqCallback reqCallback = new ReqCallback();
            // 回调方法
            reqCallback.CallBack(CORRECT_URL, token, jsonObject.toString(), handler, ResultEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}