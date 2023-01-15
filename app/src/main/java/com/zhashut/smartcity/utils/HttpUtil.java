package com.zhashut.smartcity.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static final MediaType mediaType = MediaType.Companion.parse("application/json;charset=utf-8");

    // 请求类型：application/json，带json数据
    public static void JsonReq(String url, String object, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.Companion.create(object, mediaType);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    // 请求类型：application/json，带token
    // 请求类型：application/json，带token和json参数
    public static void JsonReq(String url, String token, String object, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.Companion.create(object, mediaType);
        Request request = new Request.Builder().url(url).header("Authorization", token).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：application/json，带token和json参数
    public static void ReqWithToken(String url, String token, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).header("Authorization", token).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：application/json，不带任何参数
    public static void JsonReq(String url, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：带id的请求
    public static void JsonReqByID(String url, int id, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(url + "/" + id).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：application/json，带token和json参数 请求类型：带id的请求
    // 请求类型：application/json，带token和json参数
    public static void ReqWithTokenById(String url, String token,Integer id, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url + "/" + id).header("Authorization", token).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}