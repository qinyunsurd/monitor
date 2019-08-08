package com.surd.monitor.handler;

import com.surd.monitor.entity.MetricsVO;

/**
 * @Author: GuLang
 * @Date: Create in 16:41 2019-08-06
 * @Description:
 * @Version: 1.0
 */
public abstract class AbstractDataHandler<R extends MetricsVO,E> {

    public abstract R handlerData(E e,String type);

}
