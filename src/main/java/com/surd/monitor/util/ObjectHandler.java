package com.surd.monitor.util;

import com.surd.monitor.entity.Column;
import com.surd.monitor.entity.MetricsTableVO;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: GuLang
 * @Date: Create in 21:48 2019-08-04
 * @Description: 对象处理器, 用来将对象处理为table形式的json数据
 * @Version: 1.0
 */
public class ObjectHandler {

    public static MetricsTableVO handlerTable(
            @NotNull(message = "数据集不能为空") List<Map<String,Object>> dbSourceData) {
        MetricsTableVO tableVO = new MetricsTableVO();
        List<Column> columns = new ArrayList<>();
        List<List<Object>> rows = new ArrayList<>();
        List<Object> row = null;

        tableVO.setType("table");

        //取列
        dbSourceData.get(0).keySet().forEach(item -> {
            Column column = new Column();
            column.setText(item);
            column.setType("String");
            columns.add(column);
        });

        //取行
        for (Map<String,Object> item : dbSourceData) {
            row = new ArrayList<>();
            for (Column column : columns) {
                Object obj = item.get(column.getText());
                row.add(String.valueOf(obj));
            }
            rows.add(row);
        }
        tableVO.setColumns(columns);
        tableVO.setRows(rows);
        return tableVO;
    }

}
