package com.zhashut.smartcity.park.activity;

import static com.zhashut.smartcity.park.constant.constant.CORRECT_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.common.ReqResult;
import com.zhashut.smartcity.common.ResultEntity;

import org.json.JSONException;
import org.json.JSONObject;

public class ParkPayActivity extends AppCompatActivity implements View.OnClickListener {

    private String money = "";
    private String[] payTypes = {"微信", "支付宝", "电子支付"};
    private SharedPreferences preferences;
    private int payIndex = 0;
    private String token;
    private Handler payHandler = ReqResult.ResultHandler(this, ParkHomeActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_pay);
        initView();
    }


    /**
     * 初始化界面控件
     */
    private void initView() {
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        token = preferences.getString("token", "");
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("充值");
        TextView tv_other = findViewById(R.id.tv_other);
        tv_other.setText("充值记录");
        EditText et_money = findViewById(R.id.et_money);
        money = et_money.getText().toString().trim();
        RadioGroup rg_pay = findViewById(R.id.rg_pay);
        getPayType(rg_pay.getCheckedRadioButtonId());
        findViewById(R.id.btn_pay).setOnClickListener(this);
        tv_other.setOnClickListener(this);
    }


    /**
     * 获取支付类型
     */
    private void getPayType(int checkedId) {
        if (checkedId == R.id.rb_zfb) {
            payIndex = 1;
        } else if (checkedId == R.id.rb_dz) {
            payIndex = 2;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("money", money);
                    jsonObject.put("payType", payTypes[payIndex]);
                    ReqCallback reqCallback = new ReqCallback();
                    // 回调方法
                    reqCallback.CallBack(CORRECT_URL, token, jsonObject.toString(), payHandler, ResultEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_other:

                break;
        }
    }

}