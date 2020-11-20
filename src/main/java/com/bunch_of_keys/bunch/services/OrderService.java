package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.database.OrderBase;
import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderBase orderBase;
//    здесь должен быть service, он работает с DTO
//    DTO - состоит из примитивов, чистыми переаваемыми контроллеру
//    Service слепляет этот DTO из более сложных классов.

    public void newOrder(NewOrderRequest newOrderRequest) {

        OrderDao orderDao = new OrderDao(
                                    newOrderRequest.getStatus(),
                                    newOrderRequest.getCustomerID(),
                                    newOrderRequest.getCleanigServicesID(),
                                    newOrderRequest.getAddress(),
                                    newOrderRequest.getDateRecieved(),
                                    newOrderRequest.getDateTimeOrder(),
                                    newOrderRequest.getTotalPrice()
        );

        orderBase.addOrder(orderDao);
    }

    public List<NewOrderRequest> getOrders () {
        List<NewOrderRequest> orderRequest = new ArrayList();

        for (Map.Entry<Long, OrderDao> entry : orderBase.getOrders().entrySet()) {
            Long key = entry.getKey();
            OrderDao value = entry.getValue();

            orderRequest.add(new NewOrderRequest(
                    key,
                    value.getStatus(),
                    value.getCustomerID(),
                    value.getCleanigServicesID(),
                    value.getAddress(),
                    value.getDateRecieved(),
                    value.getDatetimeOrder(),
                    value.getTotalPrice()));
        }
        return orderRequest;
    }

    public void deleteOrder(Long id) {
        orderBase.deleteOrder(id);
    }

    public NewOrderRequest editOrder(NewOrderRequest newOrderRequest) {

        OrderDao orderDao = new OrderDao(
                newOrderRequest.getStatus(),
                newOrderRequest.getCustomerID(),
                newOrderRequest.getCleanigServicesID(),
                newOrderRequest.getAddress(),
                newOrderRequest.getDateRecieved(),
                newOrderRequest.getDateTimeOrder(),
                newOrderRequest.getTotalPrice()
        );

        orderBase.changeOrder(newOrderRequest.getId(), orderDao);
        return newOrderRequest;
    }
}
