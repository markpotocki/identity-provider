package com.mep.identityprovider.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserModel {

    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    private String email;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(username, userModel.username) &&
                Objects.equals(password, userModel.password) &&
                Objects.equals(grantedAuthorities, userModel.grantedAuthorities) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(name, userModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, grantedAuthorities, email, name);
    }
}
