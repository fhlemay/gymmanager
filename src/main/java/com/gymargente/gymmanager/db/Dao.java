package com.gymargente.gymmanager.db;

import java.util.List;
import java.util.Optional;

/**
 * Patron de conception DAO.
 * Template...
 */
public interface Dao<T> {
    void create(T t);

    Optional<T> findById(int id);

    void update(T t);

    void delete(T t);

    List<T> getAll();
}
