package com.surd.monitor.handler;

/**
 * @Author: Surd
 * @Date: Create in 16:42 2019-08-06
 * @Description:
 */
public interface ISimpleJsonData<T> {

    String getSimpleJson(T t, String type);

}
