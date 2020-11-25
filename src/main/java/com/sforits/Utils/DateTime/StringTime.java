package com.sforits.Utils.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/6/6-9:17 格式化的时间
 */
public class StringTime {

    /*
     * 获得 "yyyy/MM/dd HH:mm:ss" 格式的日期时间字符串
     * @return DateTime
     * */
    public static String getStringTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return simpleDateFormat.format(date);
    }

    /*
     * 获得 指定 格式的日期时间字符串
     * @pattern 日期时间格式
     * @return DateTime
     * */
    public static String getFormatTime(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
    }

}
