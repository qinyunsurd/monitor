package com.surd.monitor.service;

/**
 * @Author: Surd
 * @Date: Create in 16:38 2019-08-06
 * @Description:
 */
public interface IDbService<T> {

    T getDbInfo(String sql);

}
