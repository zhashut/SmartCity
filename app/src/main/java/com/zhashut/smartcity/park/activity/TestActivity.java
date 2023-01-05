package com.zhashut.smartcity.park.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.park.constant.constant;
import com.zhashut.smartcity.park.entity.ParkList;
import com.zhashut.smartcity.park.entity.ParkListField;

import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    ReqCallback callback = new ReqCallback();
    private ParkList parkListInfo;
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            parkListInfo = (ParkList) msg.obj;
            if (parkListInfo.code.equals("200")) {
                parkListSuccess(parkListInfo);
            }
        }
    };

    private void parkListSuccess(ParkList parkListInfo) {
        Intent intent = new Intent(this, ParkListActivity.class);
        intent.putExtra("parkListInfo", parkListInfo);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_correct).setOnClickListener(this);
        findViewById(R.id.btn_parkList).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_correct:
                Intent correctIntent = new Intent(this, CorrectActivity.class);
                startActivity(correctIntent);
                break;
            case R.id.btn_parkList:
                callback.CallBack(constant.PARK_LIST, handler, ParkList.class);
                break;
        }
    }
}