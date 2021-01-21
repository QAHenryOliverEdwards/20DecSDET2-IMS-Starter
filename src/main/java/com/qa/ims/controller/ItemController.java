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
        return null;
    }

    @Override
    public Item create() {
        LOGGER.info("Please enter a name");
        String name = javaUtilities.getString();
        LOGGER.info("Please enter a price");
        Double price = javaUtilities.getDouble();
        Item item = itemDao.create(new Item(name, price));
        return item;
    }

    @Override
    public Item update() {
        return null;
    }

    @Override
    public int delete() {
        return 0;
    }
}
