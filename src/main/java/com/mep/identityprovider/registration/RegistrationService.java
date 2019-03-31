package com.mep.identityprovider.registration;

import com.mep.identityprovider.user.UserDAO;
import com.mep.identityprovider.user.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RegistrationService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(RegistrationDTO userReg) {
        UserModel userModel = new UserModel();
        userModel.setUsername(userReg.getUsername());
        String encodedPassword = passwordEncoder.encode(userReg.getPassword());
        userModel.setPassword(encodedPassword);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        userModel.setGrantedAuthorities(authorities);
        userModel.setEmail(userReg.getEmail());
        userModel.setName(userReg.getName());

        try {
            this.userDAO.saveUser(userModel);
            // quick test
            System.out.println(this.userDAO.getUserDetails("test"));
        }catch(SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
        return true;
    }
}
