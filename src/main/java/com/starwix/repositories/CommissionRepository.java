package com.starwix.repositories;

import com.starwix.entities.Commission;
import com.starwix.entities.enums.Brand;
import com.starwix.entities.enums.Currency;
import com.starwix.utils.DBUtils;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by starwix on 20.9.16.
 */
public class CommissionRepository {
    private final DataSource dataSource;

    @Inject
    public CommissionRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Function<ResultSet, Commission> mapObject = rs -> {
        try {
            return new Commission(
                    rs.getInt("id"),
                    Brand.valueOf(rs.getString("brand")),
                    Currency.valueOf(rs.getString("currency")),
                    rs.getBigDecimal("value")
            );
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public void save(final List<Commission> commissions) {
        try {
            final Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (final Commission commission : commissions) {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO commission(id, brand, currency, value) VALUES(?, ?, ?, ?)");
                ps.setInt(1, commission.getId());
                ps.setString(2, commission.getBrand().name());
                ps.setString(3, commission.getCurrency().name());
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

    public List<Commission> findAll() {
        try {
            final Connection connection = dataSource.getConnection();
            final PreparedStatement ps = connection.prepareStatement("SELECT id, brand, currency, value FROM commission");
            return DBUtils.map(ps.executeQuery(), CommissionRepository.mapObject);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Commission> findByBrandAndCurrency(final Brand brand, final Currency currency) {
        try {
            final Connection connection = dataSource.getConnection();
            final PreparedStatement ps = connection.prepareStatement("SELECT id, brand, currency, value FROM commission WHERE brand = ? AND currency = ?");
            ps.setString(1, brand.name());
            ps.setString(2, currency.name());
            return DBUtils.mapSingle(ps.executeQuery(), CommissionRepository.mapObject);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
