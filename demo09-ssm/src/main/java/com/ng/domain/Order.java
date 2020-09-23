package com.ng.domain;

import java.math.BigDecimal;
import java.util.Date;


public class Order {

    private String orderID;
    private Date createTime;
    private BigDecimal price;
    private Integer status = 0;
    private Integer userID;

    public Order() {
    }

    public Order(String orderID, Date createTime, BigDecimal price, Integer status, Integer userID) {
        this.orderID = orderID;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userID=" + userID +
                '}';
    }
}
