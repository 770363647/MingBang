package com.mingbang.mingbang.mingbang.bean;

/**
 * @author: zhaojy
 * @data:On 2018/1/19.
 */

public class InforItemBean {
    private String title;
    private String time;
    private String content;
    private int logo;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {

        return time;
    }

    public String getTitle() {

        return title;
    }

    public int getLogo() {
        return logo;
    }
}
