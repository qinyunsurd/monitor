package com.surd.monitor.service;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: GuLang
 * @Date: Create in 16:20 2019-08-06
 * @Description:
 * @Version: 1.0
 */
@Component
public class LoginCounterService {
    private static AtomicInteger loginSum = new AtomicInteger(0);
    private static String today;

    public void  increaseLogin(){
        loginSum.incrementAndGet();
    }

    public int getLoginSum(){
        isNeedInit();
        return loginSum.get();
    }

    private void isNeedInit() {
        if (null == today || !date2StringDay(new Date()).equals(today)){
            init();
        }
    }

    private void init() {
        loginSum = new AtomicInteger(0);
        today = date2StringDay(new Date());
    }

    private String date2StringDay(Date date) {
        if (null == date) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
}
