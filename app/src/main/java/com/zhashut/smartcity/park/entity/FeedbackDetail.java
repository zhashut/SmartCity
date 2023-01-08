package com.zhashut.smartcity.park.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/9
 * Time: 2:20
 * Description: No Description
 */
public class FeedbackDetail implements Serializable {

    public int id;

    public int userId;

    public String createTime;

    public String title;

    public String content;
}
