package com.zhashut.smartcity.park.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.park.adapter.FeedbackListAdapter;
import com.zhashut.smartcity.park.entity.FeedbackDetail;
import com.zhashut.smartcity.park.entity.FeedbackList;

import java.util.List;

public class FeedbackListActivity extends AppCompatActivity {

    private List<FeedbackDetail> feedbackDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
        onResult();
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("反馈记录");
        ListView lv_fb = findViewById(R.id.lv_fb);
        FeedbackListAdapter feedbackListAdapter = new FeedbackListAdapter(this, feedbackDetails);
        lv_fb.setAdapter(feedbackListAdapter);
    }

    /**
     * 接收传递的数据
     */
    private void onResult() {
        Intent intent = getIntent();
        FeedbackList feedbackList = (FeedbackList) intent.getSerializableExtra("feedbackList");
        feedbackDetails = feedbackList.feedbackDetails;
    }
}