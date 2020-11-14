package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.OrderDao;


import java.util.ArrayList;
import java.util.List;


public class OrderService {

    private List<OrderDao> orders = new ArrayList();

    public List<OrderDao> getOrders() {
        return orders;
    }

    public void setOrders() {
        this.orders.add(new OrderDao(1, "Dave", 100, "new york"));
        this.orders.add(new OrderDao(2, "Carl", 300,"new york"));
        this.orders.add(new OrderDao(3, "Pete", 200,"new york"));
        this.orders.add(new OrderDao(4, "Kevin", 800,"new york"));

    }
}
