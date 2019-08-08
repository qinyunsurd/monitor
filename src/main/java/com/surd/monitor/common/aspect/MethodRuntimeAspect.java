package com.surd.monitor.common.aspect;

import com.surd.monitor.common.annotation.MethodRuntime;
import com.surd.monitor.service.MethodRunTimeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: GuLang
 * @Date: Create in 21:28 2019-08-04
 * @Description: 自定义注解实现类
 * @Version: 1.0
 */
@Aspect
@Component
public class MethodRuntimeAspect {

    @Autowired
    private MethodRunTimeService methodRunTimeService;

    @Pointcut("@annotation(com.surd.monitor.common.annotation.MethodRuntime)")
    public void runtime(){}

    @Around("runtime()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long endTime = startTime - System.currentTimeMillis();
        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String methodName = method.getAnnotation(MethodRuntime.class).methodName();
            methodRunTimeService.put(methodName,endTime);
            return obj;
        } else {
            throw new RuntimeException("类型不匹配");
        }

    }
}
