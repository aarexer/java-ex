package aarexer.crud.dao;

import aarexer.crud.model.Good;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static aarexer.crud.model.Good.*;

public final class GoodDAOImpl implements GoodDAO {
    private static final Logger logger = LogManager.getLogger();

    private final DataSource dataSource;

    public GoodDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Good good) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            logger.debug("Adding good {} to database", good);

            statement.setString(1, good.getName());
            statement.setLong(2, good.getWarehouseId());

            statement.executeUpdate();

            logger.debug("Good {} added to {} table", good, TABLE_NAME);
        } catch (SQLException e) {
            throw new DAOException("Error while adding element", e);
        }
    }

    @Override
    public void remove(Good good) {
        removeById(good.getId());
    }

    @Override
    public void removeById(long id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(REMOVE_BY_ID_SQL)) {
            logger.debug("Remove good by id: {}", id);

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
    public Optional<Good> findById(final long id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            logger.debug("Get good by id: {}", id);

            statement.setLong(1, id);

            try (final ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    final String name = res.getString(NAME_COLUMN);
                    final long warehouseId = res.getLong(WAREHOUSE_ID_COLUMN);

                    logger.debug("Good with id: {} returned", id);

                    return Optional.of(new Good(id, name, warehouseId));
                } else {
                    logger.warn("Good with id: {} doesn't exist", id);
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while finding element", e);
        }
    }

    @Override
    public List<Good> findAll() {
        logger.debug("Trying to get all elements from table: {}", TABLE_NAME);

        try (final Connection connection = dataSource.getConnection();
             final Statement stmt = connection.createStatement();
             final ResultSet rs = stmt.executeQuery(FIND_ALL_SQL)) {

            final List<Good> goods = new ArrayList<>();

            while (rs.next()) {
                final Long id = rs.getLong(ID_COLUMN);
                final String name = rs.getString(NAME_COLUMN);
                final long warehouseId = rs.getLong(WAREHOUSE_ID_COLUMN);

                goods.add(new Good(id, name, warehouseId));
            }

            if (goods.size() > 0) {
                logger.debug("Return {} rows after get all query for table {}", goods.size(), TABLE_NAME);
            } else {
                logger.debug("Empty list after get all query for table {}", TABLE_NAME);
            }

            return goods;
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
    public void addAll(final List<Good> elements) {
        logger.debug("Trying to add elements {} to the table {}", elements, TABLE_NAME);

        for (Good good : elements) {
            add(good);
        }

        logger.debug("Added all elements: {}, to the table {}", elements, TABLE_NAME);
    }
}
