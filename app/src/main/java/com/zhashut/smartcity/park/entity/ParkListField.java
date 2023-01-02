package com.zhashut.smartcity.park.entity;

import java.io.Serializable;

public class ParkListField implements Serializable {
    public int id;
    public String parkName; // 停车场名称
    public String distance; // 距离
    public String address; // 地址
    public String vacancy; // 空位个数
    public String rates; // 收费
}