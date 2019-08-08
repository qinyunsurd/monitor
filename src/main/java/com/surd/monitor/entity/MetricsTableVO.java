package com.surd.monitor.entity;

import java.util.List;

/**
 * @Author: GuLang
 * @Date: Create in 21:49 2019-08-04
 * @Description:
 * @Version: 1.0
 */
public class MetricsTableVO extends MetricsVO {
    private List<Column> columns;
    private  List<List<Object>> rows;
    private String type;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
