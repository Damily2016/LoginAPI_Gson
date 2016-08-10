package com.damily.hello.model.entity;

/**
 * Created by Dandan.Cao on 2016/8/2.
 */

public class LoginMsgBean {
    private int userId;
    private int roleId;
    private String userName;
    private String token;
    public LoginMsgBean() {
        super();
    }

    public LoginMsgBean(int userId, int roleId, String userName, String token) {
        this.userId = userId;
        this.roleId = roleId;
        this.userName = userName;
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginMsgBean{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

