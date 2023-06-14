package com.tutrit.persistence.jdbc.template.config;

import com.tutrit.persistence.core.bean.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Custom RowMapper implementation for mapping a row
 * from the ResultSet to a User object.
 * This class can be extended to customize
 * the mapping behavior.
 */
public class UserMapper implements RowMapper<User> {

    /**
     * Maps a row from the ResultSet to a User object.
     *
     * @param rs      the ResultSet to extract data from
     * @param rowNum  the current row number
     * @return the mapped User object
     * @throws RuntimeException if a SQL exception occurs during
     * the mapping process
     */
    @Override
    public User mapRow(final ResultSet rs, final int rowNum)  {
        User user = new User();
        try {
            user.setUserId(rs.getString("user_id"));
            user.setName(rs.getString("name"));
            user.setPhoneNumber(rs.getString("phone_number"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
