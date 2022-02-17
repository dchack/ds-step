package com.hope.ds.tpool.tool;

import java.util.Date;

/**
 * @author hopehack
 */
public class User {

    private String username;

    private Date createTime;

    public User(String username, Date createTime) {
        this.username = username;
        this.createTime = createTime;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
