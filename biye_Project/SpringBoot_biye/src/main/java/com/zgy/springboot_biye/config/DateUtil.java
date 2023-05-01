package com.zgy.springboot_biye.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 按照格式
 */
public class DateUtil {
    public static Date time() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        Date parse = null;
        try {
            parse = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
