package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class OrderTest {

    @Test
    public void testConstructor1() {
        Order order = new Order(1L, 1L);
        assertEquals(Long.valueOf(1L), order.getId());
        assertEquals(Long.valueOf(1L), order.getcID());

        order.setId(2L);
        order.setcID(3L);
        assertEquals(Long.valueOf(2L), order.getId());
        assertEquals(Long.valueOf(3L), order.getcID());
    }

    @Test
    public void testConstructor2() {
        Order order = new Order(1L);
        assertEquals(Long.valueOf(1L), order.getcID());
    }

    @Test
    public void testToString() {
        Order order = new Order(1L, 1L);
        String expect = "Order{id=1, cID=1}";
        assertEquals(expect, order.toString());
    }

    @Test
    public void testHashCode() {
        Order order = new Order(1L, 1L);
        Order order1 = new Order(1L, 1L);
        Order order2 = new Order(2L, 2L);

        assertEquals(order.hashCode(), order1.hashCode());
        assertNotEquals(order.hashCode(), order2.hashCode());
    }
}
