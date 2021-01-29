package com.qa.ims.cli;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.utils.DatabaseUtilities;
import com.qa.ims.utils.JavaUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IMSEntryPoint {

    public static final Logger LOGGER = LogManager.getLogger();

    private final CustomerController customers;
    private final ItemController items;
    private final OrderController orders;
    private final JavaUtilities javaUtilities;

    public IMSEntryPoint() {
        this.javaUtilities = new JavaUtilities();
        final CustomerDao custDAO = new CustomerDao();
        final ItemDao itemDao = new ItemDao();
        final OrderDao orderDao = new OrderDao();
        this.customers = new CustomerController(custDAO, javaUtilities);
        this.items = new ItemController(itemDao, javaUtilities);
        this.orders = new OrderController(orderDao, javaUtilities);
    }

    public void init() {
        DatabaseUtilities.connect();

        DomainMenu domain = null;
        do {
            LOGGER.info("Which entity would you like to use?");
            DomainMenu.printDomains();

            domain = DomainMenu.getDomain(javaUtilities);

            chooseDomain(domain);

        } while (domain != DomainMenu.STOP);
    }

    private void chooseDomain(DomainMenu domainMenu) {
        boolean changeDomain = false;
        do {

            ICrudController<?> active = null;
            ICrudControllerCalculate calculate = null;
            switch (domainMenu) {
            case CUSTOMER:
                active = this.customers;
                break;
            case ITEM:
                active = this.items;
                break;
            case ORDER:
                active = this.orders;
                calculate = this.orders;
                break;
            case STOP:
                return;
            default:
                break;
            }

            LOGGER.info("What would you like to do with " + domainMenu.name().toLowerCase() + ":");

            if(active == this.orders) {
                ActionMenu.printActions();
            }

            else {
                ActionMenu.printNonOrderActions();
            }

            ActionMenu action = ActionMenu.getAction(javaUtilities);

            if (action == ActionMenu.RETURN) {
                changeDomain = true;
            } else {
                chooseAction(active,action,calculate);
            }
        } while (!changeDomain);
    }

    public void chooseAction(ICrudController<?> crudController, ActionMenu actionMenu, ICrudControllerCalculate crudControllerCalculate) {
        switch (actionMenu) {
        case CREATE:
            crudController.create();
            break;
        case READ:
            crudController.readAll();
            break;
        case UPDATE:
            crudController.update();
            break;
        case DELETE:
            crudController.delete();
            break;
        case CALCULATE:
            crudControllerCalculate.calculateTotalPrice();
            break;
        case RETURN:
            break;
        default:
            break;
        }
    }

}
