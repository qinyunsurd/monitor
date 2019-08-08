package com.surd.monitor.consul;

/**
 * @Author: GuLang
 * @Date: Create in 15:49 2019-08-06
 * @Description:
 * @Version: 1.0
 */
public class ConsulRegistryException extends Exception {
    public ConsulRegistryException(String message) {
        super(message);
    }

    public ConsulRegistryException(Throwable cause) {
        super(cause);
    }

    public ConsulRegistryException(String message, Throwable cause) {
        super(message, cause);
    }
}
