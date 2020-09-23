package com.ng.dao;

import com.ng.domain.OrderItem;

import java.util.List;

public interface OrderItemDao {

    public int insertOrderItem(OrderItem orderItem);
    public List<OrderItem> selectOrderItemsByOrderID(String orderID);

}
