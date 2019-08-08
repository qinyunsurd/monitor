package com.surd.monitor.consul;

/**
 * @Author: GuLang
 * @Date: Create in 15:32 2019-08-06
 * @Description:
 * @Version: 1.0
 */
public class MetricsRegistryProperties {
    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
