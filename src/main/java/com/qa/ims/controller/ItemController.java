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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Showing ").append(items.size());
        stringBuilder.append(" items");
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        for (Item item : items) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("~ id: ").append(item.getId()).append("\n");
            stringBuilder1.append("~ name: ").append(item.getName()).append("\n");
            stringBuilder1.append("~ price: ").append(item.getPrice());
            LOGGER.info(stringBuilder1);
            LOGGER.info("----------------------");
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
        LOGGER.info("----------------------");
        LOGGER.info("Item created with details:");
        Item latestItem = itemDao.readLatest();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(latestItem.getId()).append("\n");
        stringBuilder.append("~ name: ").append(latestItem.getName()).append("\n");
        stringBuilder.append("~ price: ").append(latestItem.getPrice());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
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
        Item originalItem = itemDao.read(id);
        Item item = itemDao.update(new Item(id, name, price));
        LOGGER.info("----------------------");
        LOGGER.info("Item updated from:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(originalItem.getId()).append("\n");
        stringBuilder.append("~ name: ").append(originalItem.getName()).append("\n");
        stringBuilder.append("~ price: ").append(originalItem.getPrice());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        LOGGER.info("Updated to:");
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("~ id: ").append(item.getId()).append("\n");
        stringBuilder1.append("~ name: ").append(item.getName()).append("\n");
        stringBuilder1.append("~ price: ").append(item.getPrice());
        LOGGER.info(stringBuilder1);
        LOGGER.info("----------------------");
        return item;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of item you wish to delete");
        Long id = javaUtilities.getLong();
        Item item = itemDao.read(id);
        LOGGER.info("----------------------");
        LOGGER.info("Delete this item profile:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(item.getId()).append("\n");
        stringBuilder.append("~ name: ").append(item.getName()).append("\n");
        stringBuilder.append("~ price: ").append(item.getPrice());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        return itemDao.delete(id);
    }
}
