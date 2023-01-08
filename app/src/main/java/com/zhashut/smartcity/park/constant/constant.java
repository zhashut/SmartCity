package com.zhashut.smartcity.park.constant;

import static com.zhashut.smartcity.common.RequestUrl.REQUEST_URL;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/21
 * Time: 0:44
 * Description: No Description
 */
public class constant {
    // 提交纠错信息 - POST - 请求头要带 token - application/json
    public final static String CORRECT_URL = REQUEST_URL + "/prod-api/api/park/correct";

    // 查询停车场列表 - GET - 请求数据类型 - application/x-www-form-urlencoded
    public final static String PARK_LIST = REQUEST_URL + "/prod-api/api/park/lot/list?pageNum=0&pageSize=10";

    // 查询停车场详情 - GET - 请求类型 - application/x-www-form-urlencoded - /prod-api/api/park/lot/{id}
    public final static String PARK_DETAIL = REQUEST_URL + "/prod-api/api/park/lot";

    public final static String PRESS_LIST = REQUEST_URL + "/prod-api/api/park/press/press/list?pageNum=0&pageSize=15";
}
