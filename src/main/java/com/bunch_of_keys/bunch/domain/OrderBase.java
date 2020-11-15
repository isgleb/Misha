package com.bunch_of_keys.bunch.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderBase {

    private List<OrderDao> orders = new ArrayList();


    public void addOrder(OrderDao newOrder) {
        this.orders.add(newOrder);
    }

    public List<OrderDao> getOrders() {
        return orders;
    }




}
