package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DatabaseUtilities;

public class CustomerDAOTest {

    private final CustomerDao DAO = new CustomerDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Customer created = new Customer(3L, "nick", "johnson");
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Customer> expected = new ArrayList<>();
        expected.add(new Customer(1L, "jordan", "harrison"));
        expected.add(new Customer(2L, "henry", "oliver-edwards"));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Customer(2L, "henry", "oliver-edwards"), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ID = 1L;
        assertEquals(new Customer(ID, "jordan", "harrison"), DAO.read(ID));
    }

    @Test
    public void testUpdate() {
        final Customer updated = new Customer(1L, "nick", "johnson");
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.deleteOrders(2));
        assertEquals(1, DAO.delete(2));
    }

    @Test
    public void testModelFromResultSet() {
        Customer customer = DAO.readLatest();
        Customer expected = new Customer(2L,"henry", "oliver-edwards");
        assertEquals(expected, customer);
    }

    @Test
    public void testDeleteOrders() {
        assertEquals(1, DAO.deleteOrders(2));
    }

    @Test
    public void testEmptyCustomers() {

    }

    @Test
    public void testCreateFail() {
        assertNull(DAO.create(null));
    }

    @Test
    public void testReadFail() {
        assertNull(DAO.read(null));
    }

    @Test
    public void testReadAllFail() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data-empty.sql");
        assertEquals(new ArrayList<>(), DAO.readAll());
    }

    @Test
    public void testReadLatestFail() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data-empty.sql");
        assertNull(DAO.readLatest());
    }

    @Test
    public void testUpdateFail() {
        assertNull(DAO.update(null));
    }

    @Test
    public void testDeleteFail() {
        assertEquals(0, DAO.delete(0));
    }
}
