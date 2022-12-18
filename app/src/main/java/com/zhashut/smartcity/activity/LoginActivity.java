package com.zhashut.smartcity.activity;

import static com.zhashut.smartcity.constant.constant.LOGIN_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.entity.LoginRes;
import com.zhashut.smartcity.utils.Animation;
import com.zhashut.smartcity.utils.ReqCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户登录
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_psw;
    private CheckBox ck_remember;
    private ImageView iv_rotate;
    private TextView tv_wait;
    private String username;
    private String password;
    private SharedPreferences preferences;
    private LoginRes userInfo;
    ReqCallback callback;
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            userInfo = (LoginRes) msg.obj;
            if (userInfo.code.equals("200")) {
                // 登录成功
                loginSuccess(userInfo);
            } else {
                // 登录失败
                loginFailed(userInfo);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        reload();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_psw);
        ck_remember = findViewById(R.id.ck_remember);
        iv_rotate = findViewById(R.id.iv_rotate);
        tv_wait = findViewById(R.id.tv_wait);
        ck_remember.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("isRemember", isChecked);
            edit.commit();
        });
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    /**
     * 登录成功
     */
    private void loginSuccess(LoginRes user) {
        Intent intent = new Intent(this, TestActivity.class);
        SharedPreferences.Editor edit = preferences.edit();
        String token = user.token;
        edit.putString("token", token);
        edit.putString("username", username);
        edit.putString("password", password);
        edit.commit();
        startActivity(intent);
        Animation.StopAnim(this, iv_rotate, tv_wait);
    }

    /**
     * 登录失败
     */
    private void loginFailed(LoginRes userInfo) {
        et_psw.setText("");
        Toast.makeText(this, userInfo.message, Toast.LENGTH_LONG).show();
        Animation.StopAnim(this, iv_rotate, tv_wait);
    }

    /**
     * 控件绑定事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                username = et_name.getText().toString().trim();
                password = et_psw.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "登录失败，账号密码不得为空", Toast.LENGTH_SHORT).show();
                    et_psw.setText("");
                    return;
                }

                // 加载动画效果
                Animation.LoadingAnim(this, iv_rotate, tv_wait);

                // 登录加载回调
                loginLoading(username, password);

                break;
            case R.id.btn_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 记住密码
     */
    private void reload() {
        boolean isRemember = preferences.getBoolean("isRemember", false);
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        if (isRemember) {
            et_name.setText(username);
            et_psw.setText(password);
            ck_remember.setChecked(true);
        }
    }

    /**
     * 登录加载回调
     */
    private void loginLoading(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            Log.i("log", jsonObject.toString());
            // 回调类实例
            callback = new ReqCallback();
            // 回调方法
            callback.CallBack(LOGIN_URL, jsonObject.toString(), handler, LoginRes.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}