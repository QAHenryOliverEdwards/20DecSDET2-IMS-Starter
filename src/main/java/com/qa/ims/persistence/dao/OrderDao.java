package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.dao.ItemDao;

public class OrderDao implements IDomainDao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Order> readAll() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders")) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO orders(fk_c_id) VALUES (?)")) {
            statement.setLong(1, order.getC_id());
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

    public Order readLatest() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1")) {
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

    public Order addUpdate(Order order, Long itemID) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO order_items(fk_o_id, fk_i_id) VALUES(?, ?)")) {
            statement.setLong(1, order.getId());
            statement.setLong(2, itemID);
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order removeUpdate(Order order, Long itemID) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE (fk_o_id = ? AND fk_i_id = ?)")) {
            statement.setLong(1, order.getId());
            statement.setLong(2, itemID);
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Double calculateTotal(Order order) {
        Double totalPrice = 0.0;
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT items.id, items.price FROM items JOIN order_items ON items.id=order_items.fk_i_id WHERE order_items.fk_o_id = ?")) {
            statement.setLong(1, order.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totalPrice += resultSet.getDouble("price");
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return totalPrice;
    }

    @Override
    public int delete(long id) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long fk_c_id = resultSet.getLong("fk_c_id");
        return new Order(id, fk_c_id);
    }

}
