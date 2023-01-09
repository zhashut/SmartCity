package com.zhashut.smartcity.citysubway.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 接收接口返回的数据
 */
public class FeedbackList implements Serializable {

    public String code;

    @SerializedName("msg")
    public String message;

    @SerializedName("rows")
    public List<FeedbackListField> rows;

    @SerializedName("data")
    public Feedback data;

    @SerializedName("total")
    public Integer total;//总记录数

}
