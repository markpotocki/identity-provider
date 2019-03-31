package com.mep.identityprovider.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    CustomDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userDAO.getUserDetails(username);
        CustomUser customUser = new CustomUser(userModel);

        return customUser;
    }
}
