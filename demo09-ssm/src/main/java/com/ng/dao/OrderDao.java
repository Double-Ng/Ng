package com.ng.dao;

import com.ng.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    int insertOrder(Order order);
    int updateOrderStatus(@Param("orderID") String orderID, @Param("status") int status);

    List<Order> selectOrdersByUserID(int userID);
    List<Order> selectAllOrders();

}
