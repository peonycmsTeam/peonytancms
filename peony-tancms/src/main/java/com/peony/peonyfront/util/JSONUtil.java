package com.peony.peonyfront.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JSONUtil {
    /**
     * 把json转化为Map 赵树涛
     */
    public static Map json2Map(String json) {
        // JSON -> Map
        Map jsonmap = (Map) JSON.parse(json);
        Map remap = new HashMap();
        for (Object key : jsonmap.keySet()) {
            if (key.equals("FileList")) {
                List barList1 = JSON.parseArray(jsonmap.get(key).toString(), Map.class);
                remap.put(key, barList1);
            } else {
                remap.put(key, jsonmap.get(key));
            }
        }
        return remap;
    }

    /**
     * 把数组转化为json 赵树涛
     */
    public static String array2JSON2(Object[] ins) {

        String json_arr_Bar = JSON.toJSONString(ins, false);
        return json_arr_Bar;
    }

    /**
     * 把Map转化为json 赵树涛
     */
    public static String map2JSON(Map map) {

        String json = JSON.toJSONString(map);
        return json;
    }
}
