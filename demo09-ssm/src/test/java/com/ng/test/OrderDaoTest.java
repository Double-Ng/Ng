package com.ng.test;

import com.ng.dao.OrderDao;
import com.ng.dao.OrderItemDao;
import com.ng.domain.Order;
import com.ng.domain.OrderItem;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDao orderDao;
    OrderItemDao orderItemDao;

    @Before
    public void setUp() throws Exception {

        String config = "conf/applicationContext.xml";
        ApplicationContext act = new ClassPathXmlApplicationContext(config);
        orderDao = act.getBean("orderDao", OrderDao.class);
        orderItemDao = act.getBean("orderItemDao", OrderItemDao.class);

    }

    @Test
    public void OrderDaoT() {

        Order order = new Order("123456", new Date(), new BigDecimal(99), 0, 1);
//        int i = orderDao.insertOrder(order);

//        List<Order> orders = orderDao.selectAllOrders();

//        List<Order> orders = orderDao.selectOrdersByUserID(1);

//        orders.forEach(order1 -> System.out.println(order1));

        orderDao.updateOrderStatus("001", 0);

    }

    @Test
    public void orderItemDaoT() {

        List<OrderItem> orderItems = orderItemDao.selectOrderItemsByOrderID("001");
        orderItems.forEach(orderItem -> System.out.println(orderItem));

    }

    @Test
    public void selectOrdersByUserID() {
    }

    @Test
    public void selectAllOrders() {
    }
}