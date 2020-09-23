package com.ng.service.impl;

import com.ng.dao.BookDao;
import com.ng.dao.OrderDao;
import com.ng.dao.OrderItemDao;
import com.ng.domain.*;
import com.ng.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    BookDao bookDao;

    @Override
    public String createOrder(Cart cart, int userID) {

        String orderID = System.currentTimeMillis() + "" + userID;
        Order order = new Order(orderID, new Date(), cart.gettotalPrice(), 0, userID);
        orderDao.insertOrder(order);
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), orderID);
            orderItemDao.insertOrderItem(orderItem);
            Book book = bookDao.selectBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStocks(book.getStocks() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderID;
    }

    @Override
    public List<Order> querryAllOrders() {
        return orderDao.selectAllOrders();
    }

    @Override
    public List<Order> querryMyOrders(int userID) {
        return orderDao.selectOrdersByUserID(userID);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.selectOrderItemsByOrderID(orderId);
    }

    @Override
    public void receivingConfirmation(String orderID) {
        orderDao.updateOrderStatus(orderID, 2);//确认收货
    }

    @Override
    public void delivery(String orderID) {
        orderDao.updateOrderStatus(orderID, 1);//发货
    }
}
