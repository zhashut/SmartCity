package com.zhashut.smartcity.park.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/8
 * Time: 21:34
 * Description: No Description
 */
public class PressList implements Serializable {
    public String code;

    public String msg;

    @SerializedName("rows")
    public List<PressDetail> pressDetails;
}
