package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DatabaseUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemDAOTest {

    private final ItemDao DAO = new ItemDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Item created = new Item(3L, "rubber", 0.89);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Item> expected = new ArrayList<>();
        expected.add(new Item(1L, "pencil", 0.49));
        expected.add(new Item(2L, "pen", 0.99));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Item(2L, "pen", 0.99), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ID = 1L;
        assertEquals(new Item(ID, "pencil", 0.49), DAO.read(ID));
    }

    @Test
    public void testUpdate() {
        final Item updated = new Item(1L, "pencil", 0.49);
        assertEquals(updated, DAO.update(updated));
    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.deleteOrderItems(new Item(1L, "pencil", 0.49), 1L));
        assertEquals(1, DAO.delete(1));
    }
}
