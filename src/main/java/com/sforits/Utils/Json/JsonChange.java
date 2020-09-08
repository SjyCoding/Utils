package com.sforits.Utils.Json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/9/8-10:42 Created by IntelliJ IDEA.
 */
public class JsonChange<T> {
    public static JSONObject mapToJson(Map<String, Object> map) {
        String data = JSON.toJSONString(map);
        return JSON.parseObject(data);
    }

    /**
     * map中取类对象
     *
     * @param map
     * @param tClass
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> tClass, String key) {
        T t = null;
        JSONObject jsonObject = mapToJson(map);
        JSONObject object = jsonObject.getJSONObject(key);
        t = object.toJavaObject(tClass);
        return t;
    }

    /**
     * map中取list
     *
     * @param map
     * @param tClass
     * @param key
     * @return
     */
    public List<T> mapToList(Map<String, Object> map, Class<T> tClass, String key) {
        List<T> t = null;
        JSONObject jsonObject = mapToJson(map);
        JSONArray array = jsonObject.getJSONArray(key);
        t = array.toJavaList(tClass);
        return t;
    }
}