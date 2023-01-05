package com.zhashut.smartcity.park.activity;

import static com.zhashut.smartcity.park.constant.constant.PARK_DETAIL;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.park.entity.ParkDetail;
import com.zhashut.smartcity.park.entity.ParkDetailField;

public class ParkDetailActivity extends AppCompatActivity {

    private int id;
    private TextView tv_parkName;
    private TextView tv_address;
    private TextView tv_distance;
    private TextView tv_rates;
    private TextView tv_priceCaps;
    private TextView tv_vacancy;
    private TextView tv_allPark;
    private RelativeLayout rl_open;
    private ImageView iv_img;
    ReqCallback callback = new ReqCallback();

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ParkDetail parkDetail = (ParkDetail) msg.obj;
            if (parkDetail.code.equals("200")) {
                parkDetailSuccess(parkDetail.parkDetailField);
            }
        }
    };

    /**
     * 查询停车场列表成功
     */
    private void parkDetailSuccess(ParkDetailField info) {
        tv_parkName.setText(info.parkName);
        rl_open.setVisibility(info.open.equals("Y") ? View.VISIBLE : View.GONE);
        tv_address.setText(info.address);
        tv_distance.setText("距离" + info.distance + "米");
        tv_rates.setText(info.rates + "/h");
        tv_priceCaps.setText(info.priceCaps);
        tv_vacancy.setText(info.vacancy + "/");
        tv_allPark.setText(info.allPark);
//        iv_img.setImageURI(Uri.parse(REQUEST_URL + info.imgUrl));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);
        onResult();
        initView();
        parkDetailLoading();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        tv_parkName = findViewById(R.id.tv_parkName);
        tv_address = findViewById(R.id.tv_address);
        tv_distance = findViewById(R.id.tv_distance);
        tv_rates = findViewById(R.id.tv_rates);
        tv_priceCaps = findViewById(R.id.tv_priceCaps);
        tv_vacancy = findViewById(R.id.tv_vacancy);
        tv_allPark = findViewById(R.id.tv_allPark);
        rl_open = findViewById(R.id.rl_open);
        iv_img = findViewById(R.id.iv_img);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("停车场详情");
    }

    /**
     * 接收传递的数据
     */
    private void onResult() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
    }

    /**
     * 请求等待查询停车场列表
     */
    private void parkDetailLoading() {
        callback.CallBackByID(PARK_DETAIL, id, handler, ParkDetail.class);
    }

}