package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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
         for (Order order : orders) {
             LOGGER.info(order);
         }
         return orders;
    }

    @Override
    public Order create() {
        LOGGER.info("Please enter an customer id");
        Long cID = javaUtilities.getLong();
        Order order = orderDao.create(new Order(cID));
        LOGGER.info("Order created");
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
        }

        else if (action.equals("REMOVE")) {
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
        return orderDao.delete(id);
    }

    @Override
    public Double calculateTotalPrice() {
        LOGGER.info("Please enter your customer id");
        Long cID = javaUtilities.getLong();
        LOGGER.info("Please enter your order id");
        Long oID = javaUtilities.getLong();
        Double total = orderDao.calculateTotal(new Order(oID, cID));
        LOGGER.info(total);
        return total;
    }
}
