package com.damily.hello.model.entity;

/**
 * Created by Dandan.Cao on 2016/8/2.
 */
public class LoginInfo {

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private LoginMsgBean message;
    public LoginMsgBean getMessage() {
        return message;
    }
    public void setMessage(LoginMsgBean message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "status=" + status +
                ", message=" + message +
                '}';
    }

}
