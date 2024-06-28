package dev.almuntex.bankapi.springboot.service;

import dev.almuntex.bankapi.springboot.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class TransactionService {

    private final JdbcTemplate jdbcTemplate;

    private final String bankSlogan;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan, JdbcTemplate jdbcTemplate) {
        this.bankSlogan = bankSlogan;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> findAll() {
        return jdbcTemplate.query("SELECT * FROM transactions", (rs, rowNum) -> {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getObject("id").toString());
            transaction.setUserId(rs.getString("user_id"));
            transaction.setAmount(rs.getBigDecimal("amount"));
            transaction.setReference(rs.getString("reference"));
            transaction.setDateCreated(rs.getObject("date_created", OffsetDateTime.class));
            transaction.setBankSlogan(bankSlogan);
            return transaction;
        });
    }

    public List<Transaction> findByUserId(String userId) {
        return findAll().stream().filter(t -> t.getUserId().equals(userId)).toList();
    }

    public Transaction create(String userId, BigDecimal amount, String reference, OffsetDateTime dateCreated) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO transactions (user_id, amount, reference, date_created) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);
            ps.setBigDecimal(2, amount);
            ps.setString(3, reference);
            ps.setObject(4, dateCreated);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty()
                ? keyHolder.getKeys().values().iterator().next().toString()
                : null;

        Transaction transaction = new Transaction();
        transaction.setId(uuid);
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setReference(reference);
        transaction.setDateCreated(dateCreated);
        transaction.setBankSlogan(bankSlogan);
        return transaction;
    }
}
