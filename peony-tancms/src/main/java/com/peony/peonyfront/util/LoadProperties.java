package com.peony.peonyfront.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class LoadProperties {

    private static Map<String, Object> Map = new HashMap<String, Object>();

    static {
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/config/init/init.properties");
        LoadProperties(prop, in);
    }

    private static void LoadProperties(Properties prop, InputStream in) {
        try {
            prop.load(in);
            Set<Object> keys = prop.keySet();
            for (Iterator<Object> itr = keys.iterator(); itr.hasNext();) {
                String key = (String) itr.next();
                Object value = prop.get(key);
                Map.put(key, value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私有构造方法，不需要创建对象
     */
    private LoadProperties() {

    }

    public static Map<String, Object> getMap() {
        return Map;
    }

    public static void main(String args[]) {
        System.out.println(Map.get("DfsUrl"));
    }
}
