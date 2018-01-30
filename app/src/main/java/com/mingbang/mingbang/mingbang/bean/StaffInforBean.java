package com.mingbang.mingbang.mingbang.bean;

/**
 * @author: zhaojy
 * @data:On 2018/1/22.
 */

public class StaffInforBean {
    private String index;
    private String name;

    private boolean indexShow = true;

    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndexShow(boolean indexShow) {
        this.indexShow = indexShow;
    }

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public boolean getIndexShow() {
        return indexShow;
    }
}
