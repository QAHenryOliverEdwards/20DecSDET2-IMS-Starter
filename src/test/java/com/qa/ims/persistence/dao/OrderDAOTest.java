package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrderDAOTest {

    private final OrderDao DAO = new OrderDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Order created = new Order(3L, 1L);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L, 1L));
        expected.add(new Order(2L, 2L));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Order(2L, 2L), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ID = 1L;
        assertEquals(new Order(ID, 1L), DAO.read(ID));
    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.deleteOrderItems(new Order(1L, 1L)));
        assertEquals(1, DAO.delete(1));
    }

    @Test
    public void testUpdate() {
        assertNull(DAO.update(new Order(1L, 1L)));
    }

    @Test
    public void testAddUpdate() {
        final long orderID = 2L;
        final long customerID = 2L;
        final long itemID = 1L;
        assertEquals(new Order(orderID, customerID), DAO.addUpdate(new Order(orderID, customerID), itemID));
    }

    @Test
    public void testRemoveUpdate() {
        final long orderID = 1L;
        final long customerID = 1L;
        final long itemID = 1L;
        assertEquals(1L, DAO.removeUpdate(new Order(orderID, customerID), itemID));
    }

    @Test
    public void testOrderTotalHashMap() {
        HashMap<Item, Integer> listOfItemsAndQuantities = new HashMap<>();
        listOfItemsAndQuantities.put(new Item("pen", 0.99), 1);
        Order order = new Order(2L, 2L);
        HashMap<Item, Integer> expected = listOfItemsAndQuantities;
        HashMap<Item, Integer> actual = DAO.orderTotalHashMap(order);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateTotal() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("pencil", 0.49));
        Double expected = 0.49;
        Double actual = DAO.calculateTotal(items);
        assertEquals(expected, actual);
    }
}
