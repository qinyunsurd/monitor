package com.surd.monitor.consul;

/**
 * @Author: Surd
 * @Date: Create in 15:24 2019-08-06
 * @Description:
 */
public interface IServiceRegistry {

    void registry() throws Exception;

    void deregister();

    void close();
}
