package com.gymargente.gymmanager.db;

import java.io.Serial;
import java.sql.SQLException;

public class DaoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DaoException(SQLException e) {
        super(e);
    }
}
