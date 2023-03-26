package com.tutrit.jdbc.repository;

import com.tutrit.jdbc.dao.UserDAO;
import com.tutrit.jdbc.entity.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class UserRepository implements UserDAO<User,Long> {
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO sto.users (id, name, phone_number) VALUES (?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        String query = "SELECT name, phone_number FROM sto.users WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(id);
                    user.setName(rs.getString("name"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        String query = "UPDATE sto.users SET name=?, phone_number=? WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.setLong(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM sto.users WHERE id=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, id);
             ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
