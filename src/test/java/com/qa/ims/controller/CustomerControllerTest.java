package com.qa.ims.controller;

import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.JavaUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerDao customerDao;

    @Mock
    JavaUtilities javaUtilities;

    @InjectMocks
    CustomerController customerController;

    @Test
    public void testCreate() {
        when(javaUtilities.getString()).thenReturn("henry", "oliver-edwards");
        when(customerDao.create(new Customer("henry", "oliver-edwards"))).thenReturn(new Customer("henry", "oliver-edwards"));
        when(customerDao.readLatest()).thenReturn(new Customer("henry", "oliver-edwards"));
        Customer expected = new Customer("henry", "oliver-edwards");
        Customer actual = customerController.create();
        assertEquals(expected, actual);
    }

    @Test
    public void testReadAll() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("henry", "oliver-edwards"));
        customerList.add(new Customer("aaron", "mayne"));
        when(customerDao.readAll()).thenReturn(customerList);
        List<Customer> expected = customerList;
        List<Customer> actual = customerController.readAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        when(javaUtilities.getLong()).thenReturn(1L);
        when(javaUtilities.getString()).thenReturn("ben", "southwell");
        when(customerDao.read(1L)).thenReturn(new Customer(1L, "henry", "oliver-edwards"));
        when(customerDao.update(new Customer(1L,"ben", "southwell"))).thenReturn(new Customer(1L, "ben", "southwell"));
        Customer expected = new Customer(1L, "ben", "southwell");
        Customer actual = customerController.update();
        assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        when(javaUtilities.getLong()).thenReturn(1L);
        when(customerDao.read(1L)).thenReturn(new Customer(1L, "henry", "oliver-edwards"));
        when(customerDao.delete(1L)).thenReturn(1);
        int expected = 1;
        int actual = customerController.delete();
        assertEquals(expected, actual);
    }
}
