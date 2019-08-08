package com.surd.monitor.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Author: GuLang
 * @Date: Create in 21:24 2019-08-04
 * @Description: 自定义注解, 用来统计方法执行时间
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface MethodRuntime {

    /**
     * 方法名称
     * @return 方法名 string
     */
    String methodName();
}
