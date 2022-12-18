package com.zhashut.smartcity.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/18
 * Time: 17:44
 * Description: 统一响应参数类
 */
public class Result implements Serializable {
    public String code;

    @SerializedName("msg")
    public String message;
}
