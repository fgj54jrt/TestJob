package com.oyx.example.basebean;

import com.oyx.example.utils.LogUtils;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by ouyangxin
 * on 2016.09.9:47
 */
public class BaseRespose<T> implements Serializable {
    public int resultcode;
    public String resultmsg;
    public String errormgs;// 错误信息
    public T results;


    public boolean success() {
        LogUtils.logd("---------------------success");
        return 0==resultcode;
    }

    @Override
    public String toString() {
        LogUtils.logd("---------------------BaseRespose");
        return "BackData{" +
                "resultcode='" + resultcode + '\'' +
                ", resultmsg='" + resultmsg + '\'' +
                ", errormgs='" + errormgs + '\'' +
                ", results='" + results + '\'' +
                '}';
    }
}
