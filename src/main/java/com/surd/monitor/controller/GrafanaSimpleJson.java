package com.surd.monitor.controller;

import com.surd.monitor.handler.ISimpleJsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: GuLang
 * @Date: Create in 22:09 2019-08-04
 * @Description:
 * @Version: 1.0
 */
@RestController
public class GrafanaSimpleJson {
    @Autowired
    @Qualifier("tableDataHandler")
    private ISimpleJsonData<List<Map>> simpleJsonData;
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "access, content-type";
    public static final String ACCESS_CONTROL_ALLOW_METHODS_POST = "POST";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";
    public static final String TARTGETS = "targets";
    public static final String TARTGET = "target";

    @RequestMapping(value = "grafana/", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> returnTest(HttpServletRequest request, HttpServletResponse response) {
        setHeaders(response);

        Map<String, Object> map = new HashMap<>(3);
        map.put("result", "200 OK");
        return map;
    }

    @RequestMapping(value = "grafana/search", method = {RequestMethod.GET, RequestMethod.POST})
    public List search(HttpServletResponse response) {
        setHeaders(response);

        return null;
    }


    @RequestMapping(value = "grafana/query", method = {RequestMethod.GET, RequestMethod.POST})
    public String query(HttpServletResponse response, @RequestBody Map<String, Object> params) {
        setHeaders(response);
        List<Map> targetList = (List<Map>) params.get(TARTGETS);
        if (targetList.isEmpty() || targetList.get(0).isEmpty() ||
                StringUtils.isBlank(String.valueOf(targetList.get(0).get("type")))) {
            return null;
        }
        String type = String.valueOf(targetList.get(0).get("type"));
        String resultJson = null;

        switch (type) {
            case "table":
                resultJson = simpleJsonData.getSimpleJson(targetList, type);
                break;
            default:
                break;
        }

        return resultJson;
    }

    @RequestMapping(value = "grafana/annotation", method = {RequestMethod.GET, RequestMethod.POST})
    public Map annotation(HttpServletResponse response) {
        setHeaders(response);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "200 ok");
        return map;
    }

    private void setHeaders(HttpServletResponse response) {
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS_POST);
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);
    }


}
