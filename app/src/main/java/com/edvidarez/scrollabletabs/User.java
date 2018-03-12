package com.edvidarez.scrollabletabs;

/**
 * Created by edvidarez on 3/11/18.
 */

public class User {
    private String name,password;
    private Boolean isLogged;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }
}
