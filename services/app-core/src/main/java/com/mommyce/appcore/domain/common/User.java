package com.mommyce.appcore.domain.common;

import java.util.List;

/**
 * Created by israjhaliri on 8/27/17.
 */
public class User {

    String id;
    String password;
    Boolean enable;
    private List<Role> roles;

    public User() {
    }

    public User(String id, String password, Boolean enable, List<Role> roles) {
        this.id = id;
        this.password = password;
        this.enable = enable;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enable +
                ", roles=" + roles +
                '}';
    }
}
