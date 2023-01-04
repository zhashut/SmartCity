package com.zhashut.smartcity.park.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.park.adapter.ParkListAdapter;
import com.zhashut.smartcity.park.constant.constant;
import com.zhashut.smartcity.park.entity.ParkList;
import com.zhashut.smartcity.park.entity.ParkListField;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.util.List;

public class ParkListActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ParkListField> parkListFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_list);
        onResult();
        initView();
        initAdapter();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("停车场列表");
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        ListView lv_park = findViewById(R.id.lv_park);
        ParkListAdapter adapter = new ParkListAdapter(this, parkListFields);
        lv_park.setAdapter(adapter);
    }

    /**
     * 获取传递过来的数据
     */
    private void onResult() {
        Intent intent = getIntent();
        ParkList parkListInfo = (ParkList) intent.getSerializableExtra("parkListInfo");
        parkListFields = parkListInfo.rows;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}