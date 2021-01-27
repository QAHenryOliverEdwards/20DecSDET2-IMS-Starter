package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DatabaseUtilities;

public class CustomerDao implements IDomainDao<Customer> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Customer create(Customer customer) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO customers(first_name, surname) VALUES (?, ?)")) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getSurname());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Customer read(Long id) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM customers WHERE id = ?")) {
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
    public List<Customer> readAll() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customers")) {
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(modelFromResultSet(resultSet));
            }
            return customers;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Customer readLatest() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY id DESC LIMIT 1")) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE customers SET first_name = ?, surname = ? WHERE id = ?")) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getSurname());
            statement.setLong(3, customer.getId());
            statement.executeUpdate();
            return read(customer.getId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long id) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String surname = resultSet.getString("surname");
        return new Customer(id, firstName, surname);
    }

    public int deleteOrders(long cID) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE fk_c_id = ?")) {
            statement.setLong(1, cID);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

//    public List<Customer> emptyCustomers() {
//        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
//             Statement statement = connection.createStatement();
//             Statement statement1 = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
//             ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM orders");
//             PreparedStatement statement2 = connection.prepareStatement("DELETE FROM customers WHERE id = ?");
//             PreparedStatement statement3 = connection.prepareStatement("DELETE FROM orders WHERE fk_c_id = ?");
//             PreparedStatement statement4 = connection.prepareStatement("DELETE FROM order_items WHERE fk_o_id = ?")) {
//
//            while (resultSet1.next()) {
//                statement4.setLong(1, resultSet1.getLong("id"));
//                statement4.executeUpdate();
//            }
//
//            while (resultSet.next()) {
//                statement3.setLong(1, resultSet.getLong("id"));
//                statement3.executeUpdate();
//                statement2.setLong(1, resultSet.getLong("id"));
//                statement2.executeUpdate();
//            }
//
//            return readAll();
//
//        } catch (Exception e) {
//                 LOGGER.debug(e);
//                 LOGGER.error(e.getMessage());
//        }
//        return new ArrayList<>();
//    }
}

