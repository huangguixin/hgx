package com.hgx;

/**
 * 用户信息
 */
public class UserInfo {

    private Long id;

    private String userName;

    public UserInfo() {
    }

    public UserInfo(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}