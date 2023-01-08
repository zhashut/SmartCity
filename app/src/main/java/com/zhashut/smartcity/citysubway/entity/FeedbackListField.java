package com.zhashut.smartcity.citysubway.entity;

import java.io.Serializable;

/**
 * 意见反馈数据的实体类
 */
public class FeedbackListField implements Serializable {

    public Integer id;//反馈ID

    public String title;//反馈标题

    public String content;//反馈内容

    public String createTime;//创建反馈时间

    public Integer userId;//反馈人用户id



}
