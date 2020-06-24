package com.czj.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.InputStream;
import java.util.Properties;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description 工具类
 */
public class DBUtil {
    private static DruidDataSource druidDataSource;

    static {
        Properties prop = new Properties();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(in);
            druidDataSource = new DruidDataSource();
            druidDataSource.setUsername(prop.getProperty("jdbc.username"));
            druidDataSource.setPassword(prop.getProperty("jdbc.password"));
            druidDataSource.setUrl(prop.getProperty("jdbc.url"));
            druidDataSource.setDriverClassName(prop.getProperty("jdbc.driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getDataSource() {
        return druidDataSource;
    }
}
