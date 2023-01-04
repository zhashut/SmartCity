package com.zhashut.smartcity.park.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/5
 * Time: 0:48
 * Description: No Description
 */
public class ParkDetail implements Serializable {
    public String msg;

    public String code;

    @SerializedName("data")
    public ParkDetailField parkDetailField;
}
