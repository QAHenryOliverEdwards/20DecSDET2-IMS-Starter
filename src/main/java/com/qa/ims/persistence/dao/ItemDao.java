package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DatabaseUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao implements IDomainDao<Item> {

    public static final Logger LOGGER = LogManager.getLogger();

    public Item read(Long id) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM items WHERE id = ?")) {
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
    public Item readLatest() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1")) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Item create(Item item) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO items(name, price) VALUES (?, ?)")) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Item> readAll() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM items")) {
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(modelFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Item update(Item item) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("UPDATE items SET name = ?, price = ? WHERE id = ?")) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
            return read(item.getId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long id) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("DELETE FROM items WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        return new Item(id, name, price);
    }
}
