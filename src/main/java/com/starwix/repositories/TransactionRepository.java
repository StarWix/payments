package com.starwix.repositories;

import com.google.gson.Gson;
import com.starwix.entities.TransactionStored;
import com.starwix.entities.requests.TransactionRequest;
import com.starwix.exceptions.SQLRuntimeException;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Created by starwix on 21.9.16.
 */
public class TransactionRepository {
    private final DataSource dataSource;
    private final Gson gson;

    @Inject
    public TransactionRepository(final DataSource dataSource, final Gson gson) {
        this.dataSource = dataSource;
        this.gson = gson;
    }

    public Long save(final TransactionRequest request, final BigDecimal commission, final LocalDateTime createdDate) {
        try {
            final Connection connection = dataSource.getConnection();
            final PreparedStatement ps = connection.prepareStatement("INSERT INTO transaction(transaction) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            final String json = gson.toJson(TransactionStored.valueOf(request, commission, createdDate));
            ps.setString(1, json);
            ps.executeUpdate();
            final ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}
