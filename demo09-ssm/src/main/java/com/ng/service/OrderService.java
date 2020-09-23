package com.ng.service;

import com.ng.domain.Cart;
import com.ng.domain.Order;
import com.ng.domain.OrderItem;

import java.util.List;

public interface OrderService {

    public String createOrder(Cart cart, int userID);

    public List<Order> querryAllOrders();

    public List<Order> querryMyOrders(int userID);

    public List<OrderItem> showOrderDetail(String orderId);

    public void receivingConfirmation(String orderID); //收货确认

    public void delivery(String orderID);//    发货


}
