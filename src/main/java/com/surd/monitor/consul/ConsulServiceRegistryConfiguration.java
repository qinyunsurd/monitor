package com.surd.monitor.consul;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: GuLang
 * @Date: Create in 16:14 2019-08-06
 * @Description:
 * @Version: 1.0
 */
@Configuration
@ConditionalOnProperty(value = "toona.metrics.registry.enabled",havingValue = "true")
public class ConsulServiceRegistryConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "toona.metrics.registry")
    public MetricsRegistryProperties getMetricsRegistryProperties(){
        return new MetricsRegistryProperties();
    }

    @Bean
    public ConsulAutoIServiceRegistration getConsulAutoIServiceRegistration(MetricsRegistryProperties metricsRegistryProperties){
        return new ConsulAutoIServiceRegistration(metricsRegistryProperties);
    }
}
