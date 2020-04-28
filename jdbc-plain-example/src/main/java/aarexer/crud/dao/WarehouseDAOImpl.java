package aarexer.crud.dao;

import aarexer.crud.model.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static aarexer.crud.model.Warehouse.*;

public class WarehouseDAOImpl implements WarehouseDAO {
    private static final Logger logger = LogManager.getLogger();

    private final DataSource dataSource;

    public WarehouseDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Warehouse warehouse) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            logger.debug("Adding warehouse {} to database", warehouse);

            statement.setString(1, warehouse.getAddress());

            statement.executeUpdate();

            logger.debug("Warehouse {} added to {} table", warehouse, TABLE_NAME);
        } catch (SQLException e) {
            throw new DAOException("Error while adding element", e);
        }
    }

    @Override
    public void remove(Warehouse warehouse) {
        removeById(warehouse.getId());
    }

    @Override
    public void removeById(long id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(REMOVE_BY_ID_SQL)) {
            logger.debug("Remove warehouse by id: {}", id);

            statement.setLong(1, id);

            if (statement.executeUpdate() > 0) {
                logger.debug("Element with id {} removed from {} table", id, TABLE_NAME);
            } else {
                logger.error("Can't remove element with id {} from {} table", id, TABLE_NAME);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while removing element", e);
        }
    }

    @Override
    public Optional<Warehouse> findById(final long id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            logger.debug("Get warehouse by id: {}", id);

            statement.setLong(1, id);

            try (final ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    final String address = res.getString(ADDRESS_COLUMN);

                    logger.debug("Warehouse with id: {} returned", id);

                    return Optional.of(new Warehouse(id, address));
                } else {
                    logger.warn("Warehouse with id: {} doesn't exist", id);
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while finding element", e);
        }
    }

    @Override
    public List<Warehouse> findAll() {
        logger.debug("Trying to get all elements from table: {}", TABLE_NAME);

        try (final Connection connection = dataSource.getConnection();
             final Statement stmt = connection.createStatement();
             final ResultSet rs = stmt.executeQuery(FIND_ALL_SQL)) {

            final List<Warehouse> warehouses = new ArrayList<>();

            while (rs.next()) {
                final Long id = rs.getLong(ID_COLUMN);
                final String address = rs.getString(ADDRESS_COLUMN);

                warehouses.add(new Warehouse(id, address));
            }

            if (warehouses.size() > 0) {
                logger.debug("Return {} rows after get all query for table {}", warehouses.size(), TABLE_NAME);
            } else {
                logger.debug("Empty list after get all query for table {}", TABLE_NAME);
            }

            return warehouses;
        } catch (SQLException e) {
            throw new DAOException("Error while executing findAll", e);
        }
    }

    @Override
    public void removeAll() {
        logger.debug("Trying to clear table {}", TABLE_NAME);

        try (final Connection connection = dataSource.getConnection();
             final Statement statement = connection.createStatement()) {
            final int numOfRows = statement.executeUpdate(CLEAR_TABLE_SQL);

            logger.debug("Table {} cleared, removed {} rows.", TABLE_NAME, numOfRows);
        } catch (SQLException e) {
            throw new DAOException("Error while executing removeAll", e);
        }
    }

    @Override
    public void addAll(final List<Warehouse> elements) {
        logger.debug("Trying to add elements {} to the table {}", elements, TABLE_NAME);

        for (Warehouse warehouse : elements) {
            add(warehouse);
        }

        logger.debug("Added all elements: {}, to the table {}", elements, TABLE_NAME);
    }
}
