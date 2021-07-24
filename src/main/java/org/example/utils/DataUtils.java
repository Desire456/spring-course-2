package org.example.utils;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public class DataUtils {
    private DataUtils() {
    }

    public static <T> Optional<T> queryForOptional(String sql, JdbcTemplate jdbcTemplate, RowMapper<T> rowMapper, Object... args) {
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(
                sql, rowMapper, args
        )));
    }
}
