package org.example.repository;

import org.example.entity.User;
import org.example.exception.DatabaseException;
import org.example.utils.DataUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper = (rs, i) -> new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("password")
    );

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean containsUser(String userName) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT count(id) FROM users WHERE username = ?", Integer.class,
                        userName))
                .orElseThrow(DatabaseException::new) != 0;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return DataUtils.queryForOptional(
                "SELECT id, username, password FROM users WHERE username = ?",
                jdbcTemplate,
                rowMapper,
                username);
    }
}
