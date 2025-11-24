package com.realestate.repository;

import com.realestate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    private RowMapper<User> mapper = (rs, rowNum) ->
        new User(rs.getInt("id"),
                 rs.getString("name"),
                 rs.getString("email"),
                 rs.getString("role"));

    public User findByEmailAndPassword(String email, String passwordHash) {
        try {
            return jdbc.queryForObject(
                "SELECT * FROM users WHERE email=? AND password_hash=?",
                mapper,
                email, passwordHash
            );
        } catch(Exception e) {
            return null;
        }
    }

    public int createUser(String name, String email, String passwordHash) {
        return jdbc.update(
            "INSERT INTO users(name,email,password_hash,role) VALUES (?,?,?,'CUSTOMER')",
            name, email, passwordHash
        );
    }
}
