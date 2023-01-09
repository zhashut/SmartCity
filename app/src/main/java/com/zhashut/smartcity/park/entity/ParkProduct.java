package com.zhashut.smartcity.park.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/9
 * Time: 12:11
 * Description: No Description
 */
public class ParkProduct implements Serializable {

    public String code;

    public String msg;

    @SerializedName("rows")
    public List<ParkProductList> productLists;

}
