package com.mep.identityprovider.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserModel getUserDetails(String username) {

        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM USERS WHERE USERNAME=?";
        List<UserModel> list = jdbcTemplate.query(sqlQuery, new String[] {username}, (ResultSet rs, int rowNum) -> {
            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setPassword(rs.getString("PASSWORD"));
            userModel.setEmail(rs.getString("EMAIL"));
            userModel.setName(rs.getString("NAME"));
            userModel.setGrantedAuthorities( (List<GrantedAuthority>) rs.getObject("GRANTED_AUTHORITIES")); // Unsafe Cast
            return userModel;
        });

        if(list != null && list.size()>0) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSADMIN");
            grantedAuthoritiesList.add(grantedAuthority);
            return list.get(0);
        }

        return null;
    }

    public boolean saveUser(UserModel userModel) throws SQLException {
        Integer id = UUID.randomUUID().hashCode();
        String sqlQuery = "INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES (?, ?, ?)";

        jdbcTemplate.update(sqlQuery, id, userModel.getUsername(), userModel.getPassword());

        return true;
    }


}
