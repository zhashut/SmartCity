package com.zhashut.smartcity.park.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.park.adapter.PressListAdapter;
import com.zhashut.smartcity.park.entity.PressDetail;
import com.zhashut.smartcity.park.entity.PressList;

import java.util.List;

public class PressListActivity extends AppCompatActivity {

    private List<PressDetail> pressDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_list);
        onResult();
        initView();
        initAdapter();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("新闻列表");
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        ListView lv_press = findViewById(R.id.lv_press);
        PressListAdapter pressListAdapter = new PressListAdapter(this, pressDetails);
        lv_press.setAdapter(pressListAdapter);
    }

    /**
     * 接收传递的数据
     */
    private void onResult() {
        Intent intent = getIntent();
        PressList pressList = (PressList) intent.getSerializableExtra("press");
        pressDetails = pressList.pressDetails;
    }

}