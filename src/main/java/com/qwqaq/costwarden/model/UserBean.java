package com.qwqaq.costwarden.model;

public class UserBean {
    private long uid;
    private String email;
    private String name;
    private String password;
    private boolean isAdmin;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean value) {
        isAdmin = value;
    }

    public void setAdmin(int value) {
        isAdmin = value == 1;
    }
}
