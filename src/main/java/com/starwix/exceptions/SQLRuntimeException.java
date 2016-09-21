package com.starwix.exceptions;

import java.sql.SQLException;

/**
 * Created by starwix on 21.9.16.
 */
public class SQLRuntimeException extends RuntimeException {
    public SQLRuntimeException(final SQLException e) {
        super(e);
    }
}
