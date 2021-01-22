package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderController implements ICrudController<Order> {

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
        Long c_id = javaUtilities.getLong();
        Order order = orderDao.create(new Order(c_id));
        LOGGER.info("Order created");
        return order;
    }

    @Override
    public Order update() {
        return null;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long id = javaUtilities.getLong();
        return orderDao.delete(id);
    }
}
