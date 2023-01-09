package com.zhashut.smartcity.citysubway.constant;

import static com.zhashut.smartcity.common.RequestUrl.REQUEST_URL;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/18
 * Time: 16:57
 * Description: No Description
 */
public class Constant {
    // 新增意见反馈 - 需要 token 参数 请求类型：application/json
    public final static String FEEDBACK_ADD_URL = REQUEST_URL + "/prod-api/api/metro/feedback";

    // 获取意见反馈列表  请求类型：application/json
    public final static String FEEDBACK_ALL_URL = REQUEST_URL + "/prod-api/api/metro/feedback/list";

    public final static String FEEDBACK_DETAIL = REQUEST_URL + "/prod-api/api/metro/feedback/";
}
