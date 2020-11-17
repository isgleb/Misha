package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.OrderBase;
import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderBase orderBase;
//    здесь должен быть service, он работает с DTO
//    DTO - состоит из примитивов, чистыми переаваемыми контроллеру
//    Service слепляет этот DTO из более сложных классов.

    public void newOrder(NewOrderRequest createOrderRequest) {

        OrderDao orderDao = new OrderDao(createOrderRequest.getId(), createOrderRequest.getCustomer(), createOrderRequest.getPrice(), createOrderRequest.getAddress());
        orderBase.addOrder(orderDao);
    }


    public List<OrderDao> getOrders () {
        return orderBase.getOrders();
    }

    public void deleteOrder(NewOrderRequest createOrderRequest) {
        orderBase.deleteOrder(createOrderRequest);
    }

    public void setSomeOrders() {
        this.orderBase.addOrder(new OrderDao(1, "Dave", 100, "new york"));
        this.orderBase.addOrder(new OrderDao(2, "Carl", 300,"new york"));
        this.orderBase.addOrder(new OrderDao(3, "Pete", 200,"new york"));
        this.orderBase.addOrder(new OrderDao(4, "Kevin", 800,"new york"));
    }









}
