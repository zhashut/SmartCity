package com.zhashut.smartcity.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static final MediaType mediaType = MediaType.Companion.parse("application/json;charset=utf-8");

    // 请求类型：application/json
    public static void JsonReq(String url, String object, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.Companion.create(object, mediaType);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}