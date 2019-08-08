package com.surd.monitor.util;

import com.alibaba.fastjson.JSON;

/**
 * @Author: gulang
 * @Date: Create in 21:11 2019-08-04
 * @Description: json 处理,为了灵活的换取底层依赖
 *              在这里我们进行简单的封装.
 * @Version: 1.0
 */
public class JsonHandler {

    /**
     * 将传入的对象转换成json 字符串
     * @param obj 传入需要转换的对象
     * @return string
     */
    public static String toJsonString(Object obj) {
        if (null == obj) {
            return null;
        }

        return JSON.toJSONString(obj);
    }
}
