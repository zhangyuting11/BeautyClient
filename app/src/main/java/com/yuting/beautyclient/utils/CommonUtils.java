package com.yuting.beautyclient.utils;

import com.yuting.beautyclient.common.Constant;

/**
 * 通用工具类
 * Created by yuting on 2016/8/5.
 */
public class CommonUtils {

    public static String getUrl(String url, String size) {
        return Constant.BASE_IMAGE_URL + url + "_" + size;
    }

    public static String getUrl(String url) {
        return Constant.BASE_IMAGE_URL + url;
    }
}
