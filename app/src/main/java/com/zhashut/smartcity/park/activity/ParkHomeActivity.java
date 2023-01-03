package com.zhashut.smartcity.park.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.park.constant.constant;
import com.zhashut.smartcity.park.entity.ParkList;

public class ParkHomeActivity extends AppCompatActivity implements View.OnClickListener {

    ReqCallback callback = new ReqCallback();
    private ParkList parkListInfo;

    private Handler parkListHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            parkListInfo = (ParkList) msg.obj;
            if (parkListInfo.code.equals("200")) {
                parkListSuccess(parkListInfo);
            }
        }
    };

    /**
     * 初始化界面
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_home);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("停哪儿");
        TextView tvParkL = findViewById(R.id.in_park_list).findViewById(R.id.tv_tag);
        tvParkL.setText("车场查询");
        TextView tvParkS = findViewById(R.id.in_park_score).findViewById(R.id.tv_tag);
        tvParkS.setText("积分查询");
        TextView tvParkRecharge = findViewById(R.id.in_park_recharge).findViewById(R.id.tv_tag);
        tvParkRecharge.setText("充值");
        TextView tvParkC = findViewById(R.id.in_park_car).findViewById(R.id.tv_tag);
        tvParkC.setText("我的车辆");
        TextView tvParkP = findViewById(R.id.in_park_product).findViewById(R.id.tv_tag);
        tvParkP.setText("换购记录");
        TextView tvParkF = findViewById(R.id.in_park_feedback).findViewById(R.id.tv_tag);
        tvParkF.setText("意见反馈");
        TextView tvParkCorrect = findViewById(R.id.in_park_correct).findViewById(R.id.tv_tag);
        tvParkCorrect.setText("我要纠错");
        findViewById(R.id.in_park_list).setOnClickListener(this);
        findViewById(R.id.in_park_score).setOnClickListener(this);
        findViewById(R.id.in_park_recharge).setOnClickListener(this);
        findViewById(R.id.in_park_car).setOnClickListener(this);
        findViewById(R.id.in_park_product).setOnClickListener(this);
        findViewById(R.id.in_park_feedback).setOnClickListener(this);
        findViewById(R.id.in_park_correct).setOnClickListener(this);
    }

    /**
     * 查询停车场列表成功
     *
     * @param parkListInfo 停车场列表数据
     */
    private void parkListSuccess(ParkList parkListInfo) {
        Intent intent = new Intent(this, ParkListActivity.class);
        intent.putExtra("parkListInfo", parkListInfo);
        startActivity(intent);
    }

    /**
     * 控件点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.in_park_list: // 车场查询
                callback.CallBack(constant.PARK_LIST, parkListHandler, ParkList.class);
                break;
            case R.id.in_park_score: // 积分查询

                break;
            case R.id.in_park_recharge: // 充值

                break;
            case R.id.in_park_car: // 我的车辆

                break;
            case R.id.in_park_product: // 换购记录

                break;
            case R.id.in_park_feedback: // 意见反馈

                break;
            case R.id.in_park_correct: // 我要纠错
                Intent intent = new Intent(this, CorrectActivity.class);
                startActivity(intent);
                break;
        }
    }
}