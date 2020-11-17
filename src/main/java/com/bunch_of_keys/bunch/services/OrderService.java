package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.database.OrderBase;
import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderBase orderBase;

    Integer id = 1;
//    здесь должен быть service, он работает с DTO
//    DTO - состоит из примитивов, чистыми переаваемыми контроллеру
//    Service слепляет этот DTO из более сложных классов.

    public void newOrder(NewOrderRequest newOrderRequest) {

        OrderDao orderDao = new OrderDao(
                                    id++,
                                    newOrderRequest.getStatus(),
                                    newOrderRequest.getCustomerID(),
                                    newOrderRequest.getCleanigServicesID(),
                                    newOrderRequest.getAddress(),
                                    newOrderRequest.getDateRecieved(),
                                    newOrderRequest.getDatetimeOrder(),
                                    newOrderRequest.getTotalPrice()
        );

        orderBase.addOrder(orderDao);
    }

    public List<OrderDao> getOrders () {

//        for(Object entryObj : orderBase.getOrders().entrySet()){
//            Map.Entry entry =(Map.Entry) entryObj;
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }

        return orderBase.getOrders();
    }

    public void deleteOrder(Long id) {
        orderBase.deleteOrder(id);
    }

//    public void setSomeOrders() {
//        this.orderBase.addOrder(new OrderDao(1, "Dave", 100, "new york"));
//        this.orderBase.addOrder(new OrderDao(2, "Carl", 300,"new york"));
//        this.orderBase.addOrder(new OrderDao(3, "Pete", 200,"new york"));
//        this.orderBase.addOrder(new OrderDao(4, "Kevin", 800,"new york"));
//    }
}
