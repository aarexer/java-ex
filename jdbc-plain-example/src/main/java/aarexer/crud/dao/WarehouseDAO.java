package aarexer.crud.dao;

import aarexer.crud.model.Warehouse;

import static aarexer.crud.model.Warehouse.*;

public interface WarehouseDAO extends DAO<Warehouse> {
    String INSERT_SQL = "INSERT INTO " + TABLE_NAME + " (" + ADDRESS_COLUMN + ") VALUES(?)";

    String REMOVE_BY_ID_SQL = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=?";

    String FIND_BY_ID_SQL = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=?";

    String CLEAR_TABLE_SQL = "DELETE FROM " + TABLE_NAME;

    String FIND_ALL_SQL = "SELECT * FROM " + TABLE_NAME;
}
