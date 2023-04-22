package com.zgy.springboot_biye.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 网易邮箱配置类
 */
@Configuration
@PropertySource(value="classpath:WEmail.properties")
public class MailConfig {
    public static final String PROPERTIES_DEFAULT = "WEmail.properties";
    public static String host;
    public static Integer port;
    public static String userName;
    public static String passWord;
    public static String emailForm;
    public static String timeout;
    public static String personal;
    public static Properties properties;

    static {
        init();
    }

    private static void init(){
        properties = new Properties();
        try {
            //获取配置文件内容
            InputStream inputStream = MailConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            properties.load(inputStream);
            inputStream.close();
            //给属性赋值
            host = properties.getProperty("mailHost");
            port = Integer.parseInt(properties.getProperty("mailPort"));
            userName = properties.getProperty("mailUsername");
            passWord = properties.getProperty("mailPassword");
            emailForm = properties.getProperty("mailUsername");
            timeout = properties.getProperty("mailTimeout");
            //发送人
            personal = "西南石油大学招生处";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
