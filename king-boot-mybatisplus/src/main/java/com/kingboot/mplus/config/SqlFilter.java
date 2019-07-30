package com.kingboot.mplus.config;

import com.kingboot.mplus.config.exception.ParameterException;
import org.apache.commons.lang.StringUtils;

/**
 * @description： SQL过滤
 */
public class SqlFilter {

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
			//ParameterException为继承RuntimeException的自定义异常
            if (str.contains(keyword)) {
                throw new ParameterException("包含非法字符");
            }
        }

        return str;
    }
}
