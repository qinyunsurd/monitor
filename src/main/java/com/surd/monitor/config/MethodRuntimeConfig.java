package com.surd.monitor.config;

import com.surd.monitor.collect.MethodRuntimeCollector;
import io.prometheus.client.CollectorRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: GuLang
 * @Date: Create in 21:41 2019-08-04
 * @Description: 自定义指标配置注册类
 * @Version: 1.0
 */
@Configuration
@ConditionalOnClass(CollectorRegistry.class)
public class MethodRuntimeConfig {

    private final CollectorRegistry registry;
    private MethodRuntimeCollector collector;

    public MethodRuntimeConfig(CollectorRegistry registry,MethodRuntimeCollector collector) {
        this.registry = registry;
        this.collector = collector;
        this.collector.register(this.registry);
    }
}
