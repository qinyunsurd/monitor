package com.surd.monitor.entity;

import java.io.Serializable;

/**
 * @Author: GuLang
 * @Date: Create in 21:50 2019-08-04
 * @Description:
 * @Version: 1.0
 */
public class Column implements Serializable {
    private String text;
    private String type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
