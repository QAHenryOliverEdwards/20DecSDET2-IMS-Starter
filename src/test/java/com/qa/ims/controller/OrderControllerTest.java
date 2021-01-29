package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    OrderDao orderDao;

    @Mock
    JavaUtilities javaUtilities;

    @InjectMocks
    OrderController orderController;

    @Test
    public void testReadAll() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1L, 1L));
        orderList.add(new Order(2L, 2L));
        when(orderDao.readAll()).thenReturn(orderList);
        HashMap<Item, Integer> order = new HashMap<>();
        order.put(new Item("pencil", 0.49), 1);
        HashMap<Item, Integer> order1 = new HashMap<>();
        order1.put(new Item("pen", 0.89), 2);
        when(orderDao.orderTotalHashMap(new Order(1L, 1L))).thenReturn(order, order1);
        List<Order> expected = orderList;
        List<Order> actual = orderController.readAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() {
        when(javaUtilities.getLong()).thenReturn(1L);
        when(orderDao.create(new Order(1L))).thenReturn(new Order(1L));
        when(orderDao.readLatest()).thenReturn(new Order(1L));
        Order expected = new Order(1L);
        Order actual = orderController.create();
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateADD() {
        when(javaUtilities.getLong()).thenReturn(1L, 1L, 1L);
        when(javaUtilities.getString()).thenReturn("ADD");
        when(orderDao.addUpdate(new Order(1L, 1L), 1L)).thenReturn(new Order(1L, 1L));
        Order expected = new Order(1L, 1L);
        Order actual = orderController.update();
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateREMOVE() {
        when(javaUtilities.getLong()).thenReturn(1L, 1L, 1L);
        when(javaUtilities.getString()).thenReturn("REMOVE");
        assertNull(orderController.update());
    }

    @Test
    public void testUpdateFail() {
        when(javaUtilities.getLong()).thenReturn(1L, 1L);
        when(javaUtilities.getString()).thenReturn("fail");
        assertNull(orderController.update());
    }

    @Test
    public void testDelete() {
        when(javaUtilities.getLong()).thenReturn(1L);
        when(orderDao.read(1L)).thenReturn(new Order(1L, 1L));
        when(orderDao.deleteOrderItems(new Order(1L, 1L))).thenReturn(1);
        when(orderDao.delete(1L)).thenReturn(1);
        int expected = 1;
        int actual = orderController.delete();
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateTotalPrice() {
        when(javaUtilities.getLong()).thenReturn(1L);
        when(orderDao.read(1L)).thenReturn(new Order(1L, 1L));
        HashMap<Item, Integer> listOfItems = new HashMap<>();
        listOfItems.put(new Item("pencil", 0.49), 1);
        when(orderDao.orderTotalHashMap(new Order(1L, 1L))).thenReturn(listOfItems);
        List<Item> items = new ArrayList<>();
        items.add(new Item("pencil", 0.49));
        when(orderDao.calculateTotal(items)).thenReturn(0.49);
        Double expected = 0.49;
        Double actual = orderController.calculateTotalPrice();
        assertEquals(expected, actual);
    }
}
