package com.surd.monitor.config;

import com.surd.monitor.collect.LoginCounterCollector;
import com.surd.monitor.service.LoginCounterService;
import io.prometheus.client.CollectorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: GuLang
 * @Date: Create in 16:33 2019-08-06
 * @Description:
 * @Version: 1.0
 */
@Configuration
@ConditionalOnClass({LoginCounterService.class, CollectorRegistry.class})
public class LoginConfiguration {
    private final CollectorRegistry registry;

    @Autowired
    public void bindMetricsRegistryToLoginCount(LoginCounterService loginCounterService){
        LoginCounterCollector loginCounterCollector = new LoginCounterCollector(loginCounterService);
        loginCounterCollector.register(registry);
    }

    public LoginConfiguration(CollectorRegistry registry) {
        this.registry = registry;
    }
}
