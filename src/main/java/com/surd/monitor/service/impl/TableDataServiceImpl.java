package com.surd.monitor.service.impl;

import com.surd.monitor.service.IDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: GuLang
 * @Date: Create in 21:45 2019-08-04
 * @Description: 自定义指标, 用来获取数据库基本信息
 * @Version: 1.0
 */
@Component
public class TableDataServiceImpl<T> implements IDbService<T> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public T getDbInfo(String sql) {
        T maps = (T) jdbcTemplate.queryForList(sql);
        return maps;
    }
}
