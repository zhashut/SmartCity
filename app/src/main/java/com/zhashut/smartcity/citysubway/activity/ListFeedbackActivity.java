package com.zhashut.smartcity.citysubway.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.citysubway.adapter.ListFeedbackAdapter;
import com.zhashut.smartcity.citysubway.entity.FeedbackList;
import com.zhashut.smartcity.citysubway.entity.FeedbackListField;

import java.io.Serializable;
import java.util.List;

public class ListFeedbackActivity extends AppCompatActivity {


    private List<FeedbackListField> listFields;

    private ListView lv_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_feedback);
        onResult();
        initView();

    }

    /**
     * 初始化页面
     */
    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("意见反馈列表");
        initAdapter();
    }

    /**
     * 调用方法装配适配器
     */
    private void initAdapter() {
        lv_feedback = findViewById(R.id.lv_feedback);
        ListFeedbackAdapter adapter = new ListFeedbackAdapter(this,listFields);
        lv_feedback.setAdapter(adapter);
    }

    /**
     * 获取意见反馈集合
     */
    private void onResult() {
        Intent intent = getIntent();
        FeedbackList feedbackInfo = (FeedbackList) intent.getSerializableExtra("listFields");
        System.out.println(feedbackInfo);
        listFields = feedbackInfo.rows;
    }




}