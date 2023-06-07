package com.tutrit.persistence.jdbc.config;

import com.tutrit.persistence.core.bean.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setName(rs.getString("name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
    }
}
