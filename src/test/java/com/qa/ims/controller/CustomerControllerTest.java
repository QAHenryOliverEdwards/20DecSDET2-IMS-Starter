package com.qa.ims.controller;

import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.JavaUtilities;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CustomerControllerTest {

    private final CustomerDao customerDao = new CustomerDao();
    private final JavaUtilities javaUtilities = new JavaUtilities();
    private final CustomerController customerController = new CustomerController(customerDao, javaUtilities);

    @Test
    public void testConstructor() {
        assertEquals(CustomerController.class, customerController.getClass());
    }

    @Test
    public void testReadAll() {
        List<Customer> actual = customerController.readAll();
        List<Customer> expected = new ArrayList<>();
        expected.add(new Customer(1L, "jordan", "harrison"));
        expected.add(new Customer(2L, "henry", "oliver-edwards"));
        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() {
        Customer customer = new Customer("henry", "oliver-edwards");
        String firstName = "henry";
        String lastName = "oliver-edwards";
        Customer customer1 = customerController.create();
        InputStream inputStream = new ByteArrayInputStream(firstName.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        InputStream inputStream1 = new ByteArrayInputStream(lastName.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream1);
        assertEquals(customer, customer1);
    }
}
