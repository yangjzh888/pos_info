package com.cu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件属性工具类
 */

public class PropertyUtil {

    /**
     * 获取当前配置文件
     * @return
     */
    public static String getConifg() {

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = PropertyUtil.class.getResourceAsStream("/config.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty("config");
    }


    /**
     * 获取配置文件中指定key对应的value
     * @param key 配置文件的键
     * @return 配置文件的值
     */
    public static String getPropertyByKey(String key) {
        // 获取当前的配置文件(config-dev/config-prd)
        String config = getConifg();

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = PropertyUtil.class.getResourceAsStream(config);
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回配置文件中key对应的value
        return properties.getProperty(key);
    }


}
