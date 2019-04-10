package com.mep.identityprovider.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
            userModel.setGrantedAuthorities( deserialize(rs.getString("GRANTED_AUTHORITIES"))); // Unsafe Cast
            return userModel;
        });

        if(list.size() > 0) {
            return list.get(0);
        }



        return null;
    }

    public boolean saveUser(UserModel userModel) throws SQLException {
        Integer id = UUID.randomUUID().hashCode();
        String sqlQuery = "INSERT INTO USERS (ID, USERNAME, PASSWORD, GRANTED_AUTHORITIES, EMAIL, NAME) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, id, userModel.getUsername(), userModel.getPassword(), userModel.getGrantedAuthorities(), userModel.getEmail(), userModel.getName());

        return true;
    }

    private List<GrantedAuthority> deserialize(String grantedAuthoritiesString) {
        LinkedList<GrantedAuthority> gauth = new LinkedList<>();
        for(String auth : grantedAuthoritiesString.split(",")) {
            gauth.add(new SimpleGrantedAuthority(auth));
        }
        return gauth;
    }


}
