package com.mmall.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


/**
 * jsonData- 作为json返回的结构
 *
 *
 *
 * 后台收到前台的请求时一般分为两种：
 *
 * 1、页面请求：直接把我们准备好的页面返回就好了
 * 2、数据请求：除了要返回前台所需要的数据、还要告诉前台当前的数据是正常处理的还是异常处理的，我们一般都是使用json进行返回
 */
/*
    pom引入了lombok可以使用注解，让程序自动生成get和set方法；
 */
@Getter
@Setter
public class JsonData {

    private boolean ret; //返回结果

    private String msg;  //异常消息(有异常的情况下)

    private Object data; //返回前台的数据
        //只传入结果
    public JsonData(boolean ret) {
        this.ret = ret;
    }
     //成功的方法
    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }
    // 成功  --直接返回数据
    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData success() {
        return new JsonData(true);
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
