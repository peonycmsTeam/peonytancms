package com.peony.peonyfront.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取index.properties文件
 * 
 * @author sjj
 *
 */
public class IndexProperties {

    public Properties loadProperties(String resources) {
        InputStream inputstream = this.getClass().getClassLoader().getResourceAsStream(resources);
        Properties properties = new Properties();
        try {
            properties.load(inputstream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputstream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}