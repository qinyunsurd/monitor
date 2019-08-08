package com.surd.monitor.collect;

import com.surd.monitor.service.MethodRunTimeService;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author: GuLang
 * @Date: Create in 21:36 2019-08-04
 * @Description:
 * @Version: 1.0
 */
@Component
public class MethodRuntimeCollector extends Collector {
    @Override
    public List<MetricFamilySamples> collect() {

        return Arrays.asList(createGauge("custom_method_runtime_duration","method duration time"));
    }

    private GaugeMetricFamily createGauge(String metrics, String help) {
        GaugeMetricFamily metricFamily = new GaugeMetricFamily(metrics,help,
                Collections.singletonList("name"));
        for (Map.Entry<String,Double> item : MethodRunTimeService.taskData.entrySet()) {
            metricFamily.addMetric(Collections.singletonList(item.getKey()),item.getValue());
        }
        return metricFamily;
    }
}
