package com.zhashut.smartcity.park.entity;

import java.io.Serializable;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/9
 * Time: 12:11
 * Description: No Description
 */
public class ParkProductList implements Serializable {
    public int id;

    public int price; // 价格

    public int score; // 积分

    public int total; // 总数

    public int saleQuantity; // 销量

    public String createTime;

    public String name; // 商品名称

    public String cover;

}
