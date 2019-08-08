package com.surd.monitor.handler;

import com.surd.monitor.controller.GrafanaSimpleJson;
import com.surd.monitor.entity.Column;
import com.surd.monitor.entity.MetricsTableVO;
import com.surd.monitor.service.IDbService;
import com.surd.monitor.util.JsonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: GuLang
 * @Date: Create in 16:43 2019-08-06
 * @Description:
 * @Version: 1.0
 */
@Service("tableDataHandler")
public class TableDataHandler extends AbstractDataHandler<MetricsTableVO, List<Map<String, Object>>>
        implements ISimpleJsonData<List<Map>> {
    @Autowired
    private IDbService<List<Map<String, Object>>> dbService;

    @Override
    public MetricsTableVO handlerData(List<Map<String, Object>> maps, String type) {
        Assert.notEmpty(maps, "数据源不能为空");
        Assert.notNull(type, "type 不能为空");
        MetricsTableVO tableVO = new MetricsTableVO();
        List<Column> columns = new ArrayList<>();
        List<List<Object>> rows = new ArrayList<>();

        tableVO.setType(type);

        //取title
        columns.addAll(getColumns(maps.get(0)));
        //取行数据
        rows.addAll(getRows(maps, columns));
        tableVO.setColumns(columns);
        tableVO.setRows(rows);
        return tableVO;
    }

    private List<List<Object>> getRows(List<Map<String, Object>> maps, List<Column> columns) {
        Assert.notEmpty(maps, "数据源不能为空");
        Assert.notEmpty(columns, "数据源不能为空");
        List<List<Object>> rows = new ArrayList<>();
        List<Object> row;
        for (Map<String, Object> item : maps) {
            row = new ArrayList<>();
            for (Column col : columns) {
                Object obj = item.get(col.getText());
                row.add(String.valueOf(obj));
            }
            rows.add(row);
        }
        return rows;
    }

    private List<Column> getColumns(Map<String, Object> dbSourceInfo) {
        Assert.notEmpty(dbSourceInfo, "数据源不能为空");
        List<Column> columns = new ArrayList<>();
        dbSourceInfo.keySet().forEach(item -> {
            Column column = new Column();
            column.setText(item);
            column.setType("String");
            columns.add(column);
        });

        return columns;
    }


    @Override
    public String getSimpleJson(List<Map> maps, String type) {
        List<Object> result = new ArrayList<>();
        for (Map targetMap : maps) {
            String target = String.valueOf(targetMap.get(GrafanaSimpleJson.TARTGET));
            List<Map<String, Object>> dbInfo = dbService.getDbInfo(target);
            MetricsTableVO metricsTableVO = this.handlerData(dbInfo, type);
            result.add(metricsTableVO);
        }

        return JsonHandler.toJsonString(result);
    }
}
