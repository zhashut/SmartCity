package com.zhashut.smartcity.common;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.zhashut.smartcity.Login.activity.LoginActivity;
import com.zhashut.smartcity.utils.Animation;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/19
 * Time: 21:46
 * Description: No Description
 */
public class MessageRes {

    public static Handler ResultHandler(Context context) {
        return new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Result result = (Result) msg.obj;
                if (result.code.equals("200")) {
                    // 注册成功
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {
                    // 注册失败
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public static Handler ResultHandler(Context context, String successMsg) {
        return new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Result result = (Result) msg.obj;
                if (result.code.equals("200")) {
                    // 注册成功
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {
                    // 注册失败
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    // TODO 带控件形参的话，进不来回调方法
    public static Handler ResultHandler(Context context, ImageView iv_rotate, TextView tv_wait) {
        return new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Result result = (Result) msg.obj;
                if (result.code.equals("200")) {
                    // 注册成功
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    // 停止加载动画
                    Animation.StopAnim(context, iv_rotate, tv_wait);
                } else {
                    // 注册失败
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show();
                    // 停止加载动画
                    Animation.StopAnim(context, iv_rotate, tv_wait);
                }
            }
        };
    }

}
