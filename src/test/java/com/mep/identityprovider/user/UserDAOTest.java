package com.mep.identityprovider.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserDAOTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getUserDetailsTest() {
        String save = "INSERT INTO USERS(ID, USERNAME,PASSWORD,GRANTED_AUTHORITIES,EMAIL,NAME) VALUES (1, 'test', '{noop}test', 'ROLE_USER,ROLE_SYS_ADMIN', 'test@test.com', 'test')";
        jdbcTemplate.update(save);

        UserDAO userDAO = new UserDAO(this.jdbcTemplate);
        UserModel userModel = userDAO.getUserDetails("test");

        assertEquals("{noop}test", userModel.getPassword()); //pwd test
        assertEquals(List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_SYS_ADMIN")),
                userModel.getGrantedAuthorities()); // ga test
        assertEquals("test@test.com", userModel.getEmail()); // email test
        assertEquals("test", userModel.getName()); // name test
    }

    private List<GrantedAuthority> deserialize(String grantedAuthoritiesString) {
        LinkedList<GrantedAuthority> gauth = new LinkedList<>();
        for(String auth : grantedAuthoritiesString.split(",")) {
            gauth.add(new SimpleGrantedAuthority(auth));
        }
        return gauth;
    }

    @Test
    public void saveUserTest() throws Exception {
        UserDAO userDAO = new UserDAO(this.jdbcTemplate);

        UserModel user = new UserModel();
        user.setUsername("test");
        user.setPassword("{noop}test");
        user.setGrantedAuthorities(List.of(new SimpleGrantedAuthority("USER")));
        user.setEmail("test@test.com");
        user.setName("test");

        userDAO.saveUser(user);

        String username = "test";
        String sql = "SELECT * FROM USERS WHERE USERNAME=?";
        List<UserModel> list = jdbcTemplate.query(sql, new String[] {username}, (ResultSet rs, int rowNum) -> {
            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setPassword(rs.getString("PASSWORD"));
            userModel.setEmail(rs.getString("EMAIL"));
            userModel.setName(rs.getString("NAME"));
            userModel.setGrantedAuthorities( deserialize(rs.getString("GRANTED_AUTHORITIES"))); // Unsafe Cast
            return userModel;
        });

        assertTrue(user.equals(list.get(0)));
    }
}
