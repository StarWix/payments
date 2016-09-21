package com.starwix.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by starwix on 21.9.16.
 */
public class DBUtils {
    public static <T> List<T> map(final ResultSet rs, final Function<ResultSet, T> mapObject) throws SQLException {
        final List<T> result = new ArrayList<>();
        while(rs.next()) {
            result.add(mapObject.apply(rs));
        }
        return result;
    }

    public static <T> Optional<T> mapSingle(final ResultSet rs, final Function<ResultSet, T> mapObject) throws SQLException {
        final List<T> result = map(rs, mapObject);
        if (result.isEmpty()) {
            return Optional.empty();
        } else if (result.size() > 1) {
            throw new IllegalStateException(result.size() + ": " + result);
        }
        return Optional.of(result.get(0));
    }
}
