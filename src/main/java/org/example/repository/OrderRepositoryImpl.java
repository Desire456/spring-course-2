package org.example.repository;

import org.example.entity.Order;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Order> rowMapper = (rs, i) -> new Order(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("order_number"),
            rs.getInt("amount"),
            rs.getInt("currency"),
            rs.getString("return_url"),
            rs.getString("fail_url"),
            rs.getString("order_status")
    );

    public OrderRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Order> save(Order order) {
        if (order.getId() == 0) {
            return queryForOptional("INSERT INTO orders(user_id, order_number, amount, currency, " +
                            "return_url, fail_url, order_status) VALUES (?,?,?,?,?,?,?) RETURNING id, userId, " +
                            "order_number, amount, currency, return_url, fail_url, order_status",
                    rowMapper,
                    order.getUserId(), order.getOrderNumber(), order.getAmount(), order.getCurrency(),
                    order.getReturnUrl(), order.getFailUrl(), order.getOrderStatus());
        }

        return queryForOptional("UPDATE orders SET user_id = ?, order_number = ?, amount = ?," +
                        "currency = ?, return_url = ?, fail_url = ?, order_status = ? WHERE id = ? " +
                        "RETURNING id, user_id, order_number, amount, currency, return_url, fail_url, order_status",
                rowMapper,
                order.getUserId(), order.getOrderNumber(), order.getAmount(), order.getCurrency(),
                order.getReturnUrl(), order.getFailUrl(), order.getOrderStatus(), order.getId());
    }

    @Override
    public Optional<Order> findByOrderId(long orderId) {
        return queryForOptional("SELECT id, user_id, order_number, amount, currency, return_url," +
                        " fail_url, order_status FROM orders where id = ?",
                rowMapper,
                orderId);
    }

    private <T> Optional<T> queryForOptional(String sql, RowMapper<T> rowMapper, Object... args) {
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(
                sql, rowMapper, args
        )));
    }
}
