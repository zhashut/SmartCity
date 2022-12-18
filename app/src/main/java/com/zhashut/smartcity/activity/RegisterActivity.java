package com.zhashut.smartcity.activity;

import static com.zhashut.smartcity.constant.constant.REGISTER_URL;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.entity.LoginRes;
import com.zhashut.smartcity.entity.Result;
import com.zhashut.smartcity.utils.Animation;
import com.zhashut.smartcity.utils.ReqCallback;

import org.json.JSONObject;

/**
 * 用户注册
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_psw;
    private String username;
    private String password;
    private EditText et_phone;
    private String phone;
    private ImageView iv_rotate;
    private TextView tv_wait;
    private RadioGroup rg_sex;
    private int sex;
    private Result result;
    ReqCallback callback = new ReqCallback();
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            result = (Result) msg.obj;
            if (result.code.equals("200")) {
                // 登录成功
                RegisterSuccess(result);
            } else {
                // 登录失败
                RegisterFailed(result);
            }
        }
    };

    // 注册成功
    private void RegisterSuccess(Result result) {
        Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        // 停止加载动画
        Animation.StopAnim(this, iv_rotate, tv_wait);
    }

    // 注册失败
    private void RegisterFailed(Result result) {
        Toast.makeText(this, result.message, Toast.LENGTH_LONG).show();
        // 停止加载动画
        Animation.StopAnim(this, iv_rotate, tv_wait);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_psw);
        et_phone = findViewById(R.id.et_phone);
        iv_rotate = findViewById(R.id.iv_rotate);
        tv_wait = findViewById(R.id.tv_wait);
        rg_sex = findViewById(R.id.rg_sex); // 0-男 1-女
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                username = et_name.getText().toString().trim();
                password = et_psw.getText().toString().trim();
                phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "注册失败，账号密码不得为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (phone.length() != 11) {
                    Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                }
                sex = rg_sex.getCheckedRadioButtonId() == R.id.rb_male ? 0 : 1;
                // 加载动画
                Animation.LoadingAnim(this, iv_rotate, tv_wait);
                // 注册
                registerLoading(username, password, phone, sex);
                break;
        }
    }

    /**
     * 注册加载回调
     */
    private void registerLoading(String username, String password, String phone, int sex) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", username);
            jsonObject.put("password", password);
            jsonObject.put("phonenumber", phone);
            jsonObject.put("sex", sex);
            // 回调方法
            callback.CallBack(REGISTER_URL, jsonObject.toString(), handler, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}