package com.zhashut.smartcity.park.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/29
 * Time: 21:36
 * Description: No Description
 */
public class ParkList implements Serializable {

    @SerializedName("rows")
    public List<ParkListField> rows;

    public String code;

    @SerializedName("msg")
    public String message;
}
