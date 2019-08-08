package com.surd.monitor.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.Date;

/**
 * @Author: GuLang
 * @Date: Create in 16:04 2019-08-06
 * @Description:
 * @Version: 1.0
 */
@Configuration
@ConditionalOnProperty(value = "toona.metrics.registry.enabled", havingValue = "true")
public class ConsulAutoServiceRegistrationJob implements SchedulingConfigurer {
    @Autowired
    private ConsulAutoIServiceRegistration autoIServiceRegistration;
    @Value("${toona.metrics.registry.retry-period:3000000}")
    private int period;
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
            try {
                //start registering to consul
                autoIServiceRegistration.registry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },triggerContext -> {
            if (autoIServiceRegistration.isRegistered()){
                //service has been registered, quit schedule
                return null;
            }
            PeriodicTrigger trigger = new PeriodicTrigger(period);
            Date date = trigger.nextExecutionTime(triggerContext);
            //consulAutoServiceRegistrationRetryJob next trigger time:{} ,date
            return date;
        });
    }
}
