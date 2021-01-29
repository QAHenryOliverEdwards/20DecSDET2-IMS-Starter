package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.JavaUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

    @Mock
    ItemDao itemDao;

    @Mock
    JavaUtilities javaUtilities;

    @InjectMocks
    ItemController itemController;

    @Test
    public void testReadAll() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("pencil", 0.49));
        itemList.add(new Item("pen", 0.99));
        when(itemDao.readAll()).thenReturn(itemList);
        List<Item> expected = itemList;
        List<Item> actual = itemController.readAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() {
        when(javaUtilities.getString()).thenReturn("water");
        when(javaUtilities.getDouble()).thenReturn(0.79);
        when(itemDao.create(new Item("water", 0.79))).thenReturn(new Item("water", 0.79));
        when(itemDao.readLatest()).thenReturn(new Item("water", 0.79));
        Item expected = new Item("water", 0.79);
        Item actual = itemController.create();
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        when(javaUtilities.getLong()).thenReturn(1L);
        when(javaUtilities.getString()).thenReturn("pencil");
        when(javaUtilities.getDouble()).thenReturn(0.59);
        when(itemDao.read(1L)).thenReturn(new Item(1L, "pencil", 0.59));
        when(itemDao.update(new Item(1L, "pencil", 0.59))).thenReturn(new Item(1L, "pencil", 0.59));
        Item expected = new Item(1L, "pencil", 0.59);
        Item actual = itemController.update();
        assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        when(javaUtilities.getLong()).thenReturn(1L);
        when(itemDao.read(1L)).thenReturn(new Item(1L, "pencil", 0.49));
        when(itemDao.delete(1L)).thenReturn(1);
        int expected = 1;
        int actual = itemController.delete();
        assertEquals(expected, actual);
    }
}
