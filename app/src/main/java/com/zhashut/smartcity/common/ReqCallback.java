package com.zhashut.smartcity.common;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.zhashut.smartcity.utils.HttpUtil;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/18
 * Time: 18:14
 * Description: 回调类
 */
public class ReqCallback {

    // 带json参数的回调
    public <T> void CallBack(String url, String json, Handler handler, Class<T> classOfT) {
        HttpUtil.JsonReq(url, json, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 带token的回调
    public <T> void CallBack(String url, String token, String json, Handler handler, Class<T> classOfT) {
        HttpUtil.JsonReq(url, token, json, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 不带任何参数的回调
    public <T> void CallBack(String url, Handler handler, Class<T> classOfT) {
        HttpUtil.JsonReq(url, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }
}
