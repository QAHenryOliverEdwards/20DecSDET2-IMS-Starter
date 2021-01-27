package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.JavaUtilities;

public class CustomerController implements ICrudController<Customer> {

    public static final Logger LOGGER = LogManager.getLogger();

    private CustomerDao customerDao;
    private JavaUtilities javaUtilities;

    public CustomerController(CustomerDao customerDao, JavaUtilities javaUtilities) {
        super();
        this.customerDao = customerDao;
        this.javaUtilities = javaUtilities;
    }

    @Override
    public Customer create() {
        LOGGER.info("Please enter a first name");
        String firstName = javaUtilities.getString();
        LOGGER.info("Please enter a surname");
        String surname = javaUtilities.getString();
        Customer customer = customerDao.create(new Customer(firstName, surname));
        LOGGER.info("----------------------");
        LOGGER.info("Customer created with credentials:");
        Customer latestCustomer = customerDao.readLatest();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(latestCustomer.getId()).append("\n");
        stringBuilder.append("~ first name: ").append(latestCustomer.getFirstName()).append("\n");
        stringBuilder.append("~ surname: ").append(latestCustomer.getSurname());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        return customer;
    }

    @Override
    public List<Customer> readAll() {
        List<Customer> customers = customerDao.readAll();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Showing ").append(customers.size());
        stringBuilder.append(" customers");
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        for (Customer customer : customers) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("~ id: ").append(customer.getId()).append("\n");
            stringBuilder1.append("~ first name: ").append(customer.getFirstName()).append("\n");
            stringBuilder1.append("~ surname: ").append(customer.getSurname());
            LOGGER.info(stringBuilder1);
            LOGGER.info("----------------------");
        }
        return customers;
    }


    @Override
    public Customer update() {
        LOGGER.info("Please enter the id of the customer you would like to update");
        Long id = javaUtilities.getLong();
        LOGGER.info("Please enter a first name");
        String firstName = javaUtilities.getString();
        LOGGER.info("Please enter a surname");
        String surname = javaUtilities.getString();
        Customer originalCustomer = customerDao.read(id);
        Customer customer = customerDao.update(new Customer(id, firstName, surname));
        LOGGER.info("----------------------");
        LOGGER.info("Customer Updated from:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(originalCustomer.getId()).append("\n");
        stringBuilder.append("~ first name: ").append(originalCustomer.getFirstName()).append("\n");
        stringBuilder.append("~ surname: ").append(originalCustomer.getSurname());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        LOGGER.info("Updated to:");
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("~ id: ").append(customer.getId()).append("\n");
        stringBuilder1.append("~ first name: ").append(customer.getFirstName()).append("\n");
        stringBuilder1.append("~ surname: ").append(customer.getSurname());
        LOGGER.info(stringBuilder1);
        LOGGER.info("----------------------");
        return customer;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the customer you would like to delete");
        Long id = javaUtilities.getLong();
        Customer customer = customerDao.read(id);
        LOGGER.info("----------------------");
        LOGGER.info("Deleted this customer profile:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(customer.getId()).append("\n");
        stringBuilder.append("~ first name: ").append(customer.getFirstName()).append("\n");
        stringBuilder.append("~ surname: ").append(customer.getSurname());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        return customerDao.delete(id);
    }

}
