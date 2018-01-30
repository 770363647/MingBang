package com.mingbang.mingbang.mingbang.bean;

/**
 * @author: zhaojy
 * @data:On 2018/1/25.
 */

public class UserInforBean {

    private static UserInforBean uif = new UserInforBean();

    private String username;
    private String name;

    private UserInforBean() {

    }

    private static UserInforBean getInstance() {
        return uif;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

}
