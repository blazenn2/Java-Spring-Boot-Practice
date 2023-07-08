package com.example.demo.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class UserJdbcDaoService {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("dob"));
    }

    public List<User>   findAll() {
        String sql = "SELECT * FROM users";
        List<User> listOfUsers = jdbcTemplate.query(sql, UserJdbcDaoService::mapRow);
        return listOfUsers;
    }

    public User save(User user) {
        String sql = "INSERT INTO users(name, dob) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getDateOfBirth());
        String getUserSql = "SELECT * FROM users WHERE name=?";
        User fetchUser = jdbcTemplate.queryForObject(getUserSql, new Object[]{user.getName()}, UserJdbcDaoService::mapRow);
        return fetchUser;
    }

    public void updateUser(Integer id, String name, Date dateOfBirth) {
        String sql = "UPDATE users SET name=?, dob=? WHERE id=?";
        jdbcTemplate.update(sql, name, dateOfBirth, id);
    }

    public User deleteUser(Integer id) {
        User user = this.findOne(id);
        String sql = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(sql, id);
        return user;

    }

    public User findOne(Integer id) {
        String sql = "SELECT * FROM users WHERE id=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, UserJdbcDaoService::mapRow);
        return user;
    }

}
