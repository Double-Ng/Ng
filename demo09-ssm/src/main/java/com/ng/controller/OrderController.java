package com.ng.controller;

import com.ng.domain.Cart;
import com.ng.domain.Order;
import com.ng.domain.OrderItem;
import com.ng.domain.User;
import com.ng.service.BookService;
import com.ng.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/createOrder.do")
    public String createOrder(HttpServletRequest request) {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        String orderID = orderService.createOrder(cart, user.getId());
        request.getSession().setAttribute("orderID", orderID);
        return "cart/checkout";
    }

    @RequestMapping(value = "/showOrderDetail.do")
    public ModelAndView showOrderDetail(String orderID, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<OrderItem> orderItems = orderService.showOrderDetail(orderID);

        User user = (User) request.getSession().getAttribute("user");
        if ("admin".equals(user.getUsername())) {
            mv.addObject("orderAdminDetails", orderItems);
            mv.setViewName("manager/orderItem_manager");
        } else {
            mv.addObject("orderUserDetails", orderItems);
            mv.setViewName("order/orderItem_user");
        }
        return mv;
    }

    @RequestMapping(value = "/receiverOrder.do")//  确认收货
    public String receiverOrder(int status, String orderID) {
        orderService.receivingConfirmation(orderID);
        return "forward:/order/showAllOrders.do";
    }

    @RequestMapping(value = "/showAllOrders.do")
    public ModelAndView showAllOrders(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        if ("admin".equals(user.getUsername())) {
            List<Order> orders = orderService.querryAllOrders();
            mv.addObject("orders", orders);
            mv.setViewName("manager/order_manager");
        } else {
            List<Order> orders = orderService.querryMyOrders(user.getId());
            mv.addObject("user_orders", orders);
            mv.setViewName("order/order");
        }
        return mv;
    }

    @RequestMapping(value = "/changeOrderStatus.do")// 发货
    public String changeOrderStatus(String orderID) {
        orderService.delivery(orderID);
        return "forward:/order/showAllOrders.do";
    }

}
