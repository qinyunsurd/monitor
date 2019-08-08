package com.surd.monitor.collect;

import com.surd.monitor.service.LoginCounterService;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @Author: GuLang
 * @Date: Create in 16:19 2019-08-06
 * @Description:
 * @Version: 1.0
 */
public class LoginCounterCollector extends Collector {

    private final LoginCounterService loginCounterService;

    public LoginCounterCollector(LoginCounterService loginCounterService) {
        this.loginCounterService = loginCounterService;
    }

    @Override
    public List<MetricFamilySamples> collect() {
        return Arrays.asList(createGauge("app_login","app login count", Collections.singletonList("app"),
                Collections.singletonList("toona"),
                loginCounter -> Double.valueOf(loginCounter.getLoginSum())));
    }

    private GaugeMetricFamily createGauge(String metrics, String help, List<String> labelName,
                                          List<String> labelValues, Function<LoginCounterService,Double> metricValueFunction){
        GaugeMetricFamily metricFamily = new GaugeMetricFamily(metrics,help,labelName);
        metricFamily.addMetric(labelValues,metricValueFunction.apply(loginCounterService));
        return metricFamily;
    }
}
