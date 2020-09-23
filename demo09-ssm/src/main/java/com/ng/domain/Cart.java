package com.ng.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Integer totalCount;      // 商品总数量
    private BigDecimal totalPrice;  // 商品总金额
    //    private List<CartItem> items = new ArrayList<CartItem>();  // 商品列表
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();


    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, LinkedHashMap<Integer, CartItem> items) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    /////////////////////////////////////////////////////////////////////////
    public void addItem(CartItem cartitem) {
        CartItem item = items.get(cartitem.getId());
        if (item == null) {
            items.put(cartitem.getId(), cartitem);
        } else {
            item.setCount(item.getCount() + 1);
            item.settotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(int id) {
        items.remove(id);
    }

    public void updateCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
            item.settotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));

        }
    }


    public void clear() {
        items.clear();
    }

///////////////////////////////////////////////////////////////

    public Integer getTotalCount() {
        totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal gettotalPrice() {
        totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().gettotalPrice());
        }
        return totalPrice;
    }


    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem(1, "富婆联系手册", 1, new BigDecimal(8.9));
        CartItem cartItem2 = new CartItem(2, "富婆联系手册", 1, new BigDecimal(10));

        cart.addItem(cartItem);
        System.out.println(cart.getTotalCount() + "---" + cart.gettotalPrice());
        cart.addItem(cartItem);
        System.out.println(cart.getTotalCount() + "---" + cart.gettotalPrice());
        cart.addItem(cartItem2);
        System.out.println(cart.getTotalCount() + "---" + cart.gettotalPrice());

    }

}

