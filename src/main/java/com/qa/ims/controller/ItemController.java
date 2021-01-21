package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.JavaUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemController implements ICrudController<Item> {

    public static final Logger LOGGER = LogManager.getLogger();

    private ItemDao itemDao;
    private JavaUtilities javaUtilities;

    public ItemController(ItemDao itemDao, JavaUtilities javaUtilities) {
        super();
        this.itemDao = itemDao;
        this.javaUtilities = javaUtilities;
    }

    @Override
    public List<Item> readAll() {
        List<Item> items = itemDao.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }

    @Override
    public Item create() {
        LOGGER.info("Please enter item name");
        String name = javaUtilities.getString();
        LOGGER.info("Please enter a price");
        Double price = javaUtilities.getDouble();
        Item item = itemDao.create(new Item(name, price));
        LOGGER.info("Item create");
        return item;
    }

    @Override
    public Item update() {
        LOGGER.info("Please enter the id of the item you would like to update");
        Long id = javaUtilities.getLong();
        LOGGER.info("Please enter item name");
        String name = javaUtilities.getString();
        LOGGER.info("Please enter a price");
        Double price = javaUtilities.getDouble();
        Item item = itemDao.update(new Item(id, name, price));
        LOGGER.info("Item updated");
        return item;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of item you wish to delete");
        Long id = javaUtilities.getLong();
        return itemDao.delete(id);
    }
}
