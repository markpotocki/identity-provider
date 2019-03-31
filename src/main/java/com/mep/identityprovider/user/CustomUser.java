package com.mep.identityprovider.user;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

    public CustomUser(UserModel userModel) {
        super(userModel.getUsername(), userModel.getPassword(), userModel.getGrantedAuthorities());
    }
}
