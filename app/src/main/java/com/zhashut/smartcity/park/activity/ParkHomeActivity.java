package com.zhashut.smartcity.park.activity;

import static com.zhashut.smartcity.park.constant.constant.PARK_PRODUCT;
import static com.zhashut.smartcity.park.constant.constant.PRESS_LIST;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.park.adapter.ParkHomeAdapter;
import com.zhashut.smartcity.park.constant.constant;
import com.zhashut.smartcity.park.entity.ParkList;
import com.zhashut.smartcity.park.entity.ParkProduct;
import com.zhashut.smartcity.park.entity.PressList;

public class ParkHomeActivity extends AppCompatActivity implements View.OnClickListener {

    ReqCallback callback = new ReqCallback();
    private ParkList parkListInfo;
    private PressList pressList;

    /**
     * 停车场列表回调
     */
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
     * 获取新闻列表回调
     */
    private Handler pressListHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            pressList = (PressList) msg.obj;
            initAdapter();
        }
    };

    /**
     * 换购列表接口回调
     */
    private Handler parkProductHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ParkProduct parkProduct = (ParkProduct) msg.obj;
            if (parkProduct.code.equals("200")) {
                parkProductListSuccess(parkProduct);
            }
        }
    };

    /**
     * 查询换购记录
     */
    private void parkProductListSuccess(ParkProduct parkProduct) {
        Intent parkProductIntent = new Intent(this, ParkProductActivity.class);
        parkProductIntent.putExtra("parkProduct", parkProduct);
        startActivity(parkProductIntent);
    }


    /**
     * 初始化界面
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_home);
        initView();
        pressLoading();
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
        findViewById(R.id.tv_new_more).setOnClickListener(this);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        ListView lv_press = findViewById(R.id.lv_press);
        ParkHomeAdapter parkHomeAdapter = new ParkHomeAdapter(this, pressList.pressDetails);
        lv_press.setAdapter(parkHomeAdapter);
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
     * 请求等待获取新闻列表
     */
    private void pressLoading() {
        callback.CallBack(PRESS_LIST, pressListHandler, PressList.class);
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
                Intent payIntent = new Intent(this, ParkPayActivity.class);
                startActivity(payIntent);
                break;
            case R.id.in_park_car: // 我的车辆

                break;
            case R.id.in_park_product: // 换购记录
                callback.CallBack(PARK_PRODUCT, parkProductHandler, ParkProduct.class);
                break;
            case R.id.in_park_feedback: // 意见反馈
                Intent feedbackIntent = new Intent(this, FeedbackActivity.class);
                startActivity(feedbackIntent);
                break;
            case R.id.in_park_correct: // 我要纠错
                Intent correctIntent = new Intent(this, CorrectActivity.class);
                startActivity(correctIntent);
                break;
            case R.id.tv_new_more: // 更多(新闻)
                Intent pressIntent = new Intent(this, PressListActivity.class);
                pressIntent.putExtra("press", pressList);
                startActivity(pressIntent);
        }
    }

}