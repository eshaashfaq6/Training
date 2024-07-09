package com.project.second.session2.users;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDataService {

    private final JdbcTemplate jdbcTemplate;

    public UserDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Users findByUserName(String username) {
        return jdbcTemplate.queryForObject("select * from users where username = ?", Users.class, username);
    }
}
