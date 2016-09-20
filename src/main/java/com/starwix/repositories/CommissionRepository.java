package com.starwix.repositories;

import com.starwix.entities.Commission;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by starwix on 20.9.16.
 */
public class CommissionRepository {
    private final DataSource dataSource;

    @Inject
    public CommissionRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(final List<Commission> commissions) {
        try {
            final Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (final Commission commission : commissions) {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO commission(id, brand, currency, value) VALUES(?, ?, ?, ?)");
                ps.setInt(1, commission.getId());
                ps.setString(2, commission.getBrand());
                ps.setString(3, commission.getCurrency().toString());
                ps.setBigDecimal(4, commission.getValue());
                ps.executeUpdate();
            }
            connection.commit();

            connection.setAutoCommit(true);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        try {
            dataSource.getConnection().prepareStatement("TRUNCATE TABLE commission").executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
