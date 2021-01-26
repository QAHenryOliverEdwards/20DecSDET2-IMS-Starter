package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ItemTest {

    @Test
    public void testConstructor1() {
        Item item = new Item("pencil", 0.49);
        assertEquals("pencil", item.getName());
        assertEquals(Double.valueOf(0.49), item.getPrice());
    }

    @Test
    public void testConstructor2() {
        Item item = new Item(1L, "pencil", 0.49);
        assertEquals(Long.valueOf(1L), item.getId());
        assertEquals("pencil", item.getName());
        assertEquals(Double.valueOf(0.49), item.getPrice());
    }

    @Test
    public void testToString() {
        Item item = new Item(1L, "pencil", 0.49);
        String expected = "Item{id=1, name='pencil', price=0.49}";
        assertEquals(expected, item.toString());
    }

    @Test
    public void testHashCode() {
        Item item = new Item(1L, "pencil", 0.49);
        Item item1 = new Item(1L, "pencil", 0.49);
        Item item2 = new Item(2L, "pen", 0.99);

        assertEquals(item.hashCode(), item1.hashCode());
        assertNotEquals(item.hashCode(), item2.hashCode());
    }

}
