package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController implements ICrudController<Order>, ICrudControllerCalculate {

    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDao orderDao;
    private JavaUtilities javaUtilities;

    public OrderController(OrderDao orderDao, JavaUtilities javaUtilities) {
        super();
        this.orderDao = orderDao;
        this.javaUtilities = javaUtilities;
    }

    @Override
    public List<Order> readAll() {
        List<Order> orders = orderDao.readAll();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Showing ").append(orders.size());
        stringBuilder.append(" orders");
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        for (Order order : orders) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("~ id: ").append(order.getId()).append("\n");
            stringBuilder1.append("~ customer id: ").append(order.getcID());
            LOGGER.info(stringBuilder1);
            HashMap<Item, Integer> orderItemsAndPrice = orderDao.orderTotalHashMap(order);
            List<Item> items = new ArrayList<>();
            for (Map.Entry<Item, Integer> entry : orderItemsAndPrice.entrySet()) {
                LOGGER.info("======================");
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("\t~ name: ").append(entry.getKey().getName()).append("\n");
                stringBuilder2.append("\t~ quantity: ").append(entry.getValue()).append("\n");
                stringBuilder2.append("\t~ price: £").append(entry.getKey().getPrice() * entry.getValue());
                LOGGER.info(stringBuilder2);
                for (int i = 1; i <= entry.getValue(); i++) {
                    items.add(entry.getKey());
                }
            }
            LOGGER.info("======================");
            LOGGER.info(String.format("~ total: £%.2f", orderDao.calculateTotal(items)));
            LOGGER.info("----------------------");
        }
        return orders;
    }

    @Override
    public Order create() {
        LOGGER.info("Please enter an customer id");
        Long cID = javaUtilities.getLong();
        Order order = orderDao.create(new Order(cID));
        LOGGER.info("----------------------");
        LOGGER.info("Customer created with credentials:");
        Order latestOrder = orderDao.readLatest();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(latestOrder.getId());
        stringBuilder.append("~ customer id: ").append(latestOrder.getcID());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        return order;
    }

    @Override
    public Order update() {
        LOGGER.info("Please enter your customer id");
        Long cID = javaUtilities.getLong();
        LOGGER.info("Please enter your order id");
        Long oID = javaUtilities.getLong();
        LOGGER.info("Would you like to ADD or REMOVE an item");
        String action = javaUtilities.getString();
        if (action.equals("ADD")) {
            LOGGER.info("Enter the id of the item you would like to add");
            Long iID = javaUtilities.getLong();
            return orderDao.addUpdate(new Order(oID, cID), iID);
        } else if (action.equals("REMOVE")) {
            LOGGER.info("Enter the id of the item you would like to remove");
            Long iID = javaUtilities.getLong();
            orderDao.removeUpdate(new Order(oID, cID), iID);
            return null;
        }
        return null;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete");
        Long id = javaUtilities.getLong();
        Order order = orderDao.read(id);
        LOGGER.info("----------------------");
        LOGGER.info("Deleted this order profile:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(order.getId()).append("\n");
        stringBuilder.append("~ customer id: ").append(order.getcID());
        LOGGER.info(stringBuilder);
        LOGGER.info("----------------------");
        System.out.println(orderDao.deleteOrderItems(order));
        return orderDao.delete(id);
    }

    @Override
    public Double calculateTotalPrice() {
        LOGGER.info("Please enter your order id:");
        Long id = javaUtilities.getLong();
        Order order = orderDao.read(id);
        LOGGER.info("----------------------");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~ id: ").append(order.getId()).append("\n");
        stringBuilder.append("~ customer id: ").append(order.getcID());
        LOGGER.info(stringBuilder);
        HashMap<Item, Integer> listOfItems = orderDao.orderTotalHashMap(order);
        List<Item> items = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry : listOfItems.entrySet()) {
            LOGGER.info("======================");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("\t~ name: ").append(entry.getKey().getName()).append("\n");
            stringBuilder2.append("\t~ quantity: ").append(entry.getValue()).append("\n");
            stringBuilder2.append("\t~ price: £").append(entry.getKey().getPrice() * entry.getValue());
            LOGGER.info(stringBuilder2);
            for (int i = 1; i <= entry.getValue(); i++) {
                items.add(entry.getKey());
            }
        }
        LOGGER.info("======================");
        LOGGER.info(String.format("~ total: £%.2f", orderDao.calculateTotal(items)));
        LOGGER.info("----------------------");

        return orderDao.calculateTotal(items);
    }
}

