package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.CustomerDao;
import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.domain.OrderDaoRepository;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDaoRepository orderDaoRepository;

    public void newOrder(NewOrderRequest newOrderRequest) {

        OrderDao orderDao = new OrderDao(
                                    newOrderRequest.getStatus(),
                                    newOrderRequest.getCustomerID(),
                                    newOrderRequest.getCleaningServicesID(),
                                    newOrderRequest.getAddress(),
                                    newOrderRequest.getDateReceived(),
                                    newOrderRequest.getDateTimeOrder(),
                                    newOrderRequest.getTotalPrice()
        );
        orderDaoRepository.save(orderDao);

    }

    public Iterable<OrderDao> getOrders () {
        Iterable<OrderDao> orderRequest = orderDaoRepository.findAll();
        return orderRequest;
    }

    public Long getCustomerId (Long orderId) {
        OrderDao theOrder= orderDaoRepository.findById(orderId).get();
        Long customerId = theOrder.getCustomerID();
        return customerId;
    }

    public void deleteOrder(Long id) {
        orderDaoRepository.deleteById(id);
    }

    public NewOrderRequest editOrder(NewOrderRequest newOrderRequest) {

        OrderDao orderDao = new OrderDao(
                newOrderRequest.getStatus(),
                newOrderRequest.getCustomerID(),
                newOrderRequest.getCleaningServicesID(),
                newOrderRequest.getAddress(),
                newOrderRequest.getDateReceived(),
                newOrderRequest.getDateTimeOrder(),
                newOrderRequest.getTotalPrice()
        );

        orderDaoRepository.save(orderDao);

        return newOrderRequest;
    }
}
