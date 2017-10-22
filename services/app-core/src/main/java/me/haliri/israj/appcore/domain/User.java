package me.haliri.israj.appcore.domain;

import java.util.Set;

/**
 * Created by israjhaliri on 8/27/17.
 */
public class User {

    String id;
    String password;
    Boolean enabled;
    private Set<Role> roles;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                ", roles=" + roles +
                '}';
    }
}
