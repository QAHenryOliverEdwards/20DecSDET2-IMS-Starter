package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements IDomainDao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Order> readAll() {
        return null;
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO orders(id) VALUES (?)")) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order read(Long id) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection
        .prepareStatement("SELECT * FROM orders WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public int delete(long id) {
        return 0;
    }

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

}
