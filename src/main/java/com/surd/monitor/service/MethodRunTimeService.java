package com.surd.monitor.service;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: GuLang
 * @Date: Create in 21:18 2019-08-04
 * @Description:
 * @Version: 1.0
 */
@Component
public class MethodRunTimeService {
    public static final Map<String,Double> taskData = new ConcurrentHashMap<>();

    public void put(@NotNull(message = "方法名不能为空") String methodName,
                    @NotNull(message = "执行时间不能为空") Long date){
        taskData.put(methodName,date2Double(date));
    }

    private double date2Double(Long date) {
        return Double.valueOf(date);
    }
}
