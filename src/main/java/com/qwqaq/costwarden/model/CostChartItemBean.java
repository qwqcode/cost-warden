package com.qwqaq.costwarden.model;

/**
 * 饼图
 */
public class CostChartItemBean {
    private String name;
    private String value;

    public CostChartItemBean() {
    }

    public CostChartItemBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
