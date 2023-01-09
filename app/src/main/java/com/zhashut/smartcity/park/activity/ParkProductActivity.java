package com.zhashut.smartcity.park.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.park.adapter.ParkProductAdapter;
import com.zhashut.smartcity.park.entity.ParkProduct;
import com.zhashut.smartcity.park.entity.ParkProductList;

import java.util.List;

public class ParkProductActivity extends AppCompatActivity {

    private List<ParkProductList> productLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_product);
        onResult();
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("换购记录");
        ListView lv_product = findViewById(R.id.lv_product);
        ParkProductAdapter parkProductAdapter = new ParkProductAdapter(this, productLists);
        lv_product.setAdapter(parkProductAdapter);
    }

    /**
     * 接收传递的数据
     */
    private void onResult() {
        Intent intent = getIntent();
        ParkProduct parkProduct = (ParkProduct) intent.getSerializableExtra("parkProduct");
        productLists = parkProduct.productLists;
    }
}