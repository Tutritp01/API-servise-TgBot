package com.tutrit.jdbc.repository;


import com.tutrit.jdbc.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
@Primary
public class UserRepository implements UserDAO {
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {
        String query = """
                INSERT INTO sto.users ( name, phone_number) 
                VALUES (?, ?)
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setUserId(generatedKeys.getLong(1));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user to database: " + e.getMessage(), e);
        }
    }

    @Override
    public User findById(Long id) {
        String query = """
                SELECT name, phone_number 
                FROM sto.users 
                WHERE id = ?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            User user = new User();
            if (rs.next()) {
                user.setUserId(id);
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user to database: " + e.getMessage(), e);
        }
    }

    public void update(User user) {
        String query = """
                UPDATE sto.users 
                SET name=?, phone_number=? 
                WHERE id=?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.setLong(3, user.getUserId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user to database: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = """
                DELETE FROM sto.users 
                WHERE id=?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user to database: " + e.getMessage(), e);
        }
    }

}
