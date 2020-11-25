package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.Order;
import com.bunch_of_keys.bunch.domain.OrderRepository;
import com.bunch_of_keys.bunch.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void newOrder(OrderDto orderDto) {

        Order order = new Order(
                                    orderDto.getStatus(),
                                    orderDto.getCustomerID(),
                                    orderDto.getCleaningServicesID(),
                                    orderDto.getAddress(),
                                    orderDto.getDateReceived(),
                                    orderDto.getDateTimeOrder(),
                                    orderDto.getTotalPrice()
        );
        orderRepository.save(order);

    }

    public List<OrderDto> getOrders () {
        Iterable<Order> orders = orderRepository.findAll();
        List<OrderDto> ordersResponse = new ArrayList<>();

        for (Order order : orders) {
            ordersResponse.add(new OrderDto(order.getId(),
                    order.getStatus(),
                    order.getCustomerID(),
                    order.getCleaningServicesID(),
                    order.getAddress(),
                    order.getDateReceived(),
                    order.getDateTimeOrder(),
                    order.getTotalPrice()));
        }
        return ordersResponse;
    }

    public Long getCustomerId (Long orderId) {
        Order theOrder= orderRepository.findById(orderId).get();
        Long customerId = theOrder.getCustomerID();
        return customerId;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto editOrder(OrderDto orderDto) {

        Order order = new Order(
                orderDto.getStatus(),
                orderDto.getCustomerID(),
                orderDto.getCleaningServicesID(),
                orderDto.getAddress(),
                orderDto.getDateReceived(),
                orderDto.getDateTimeOrder(),
                orderDto.getTotalPrice()
        );

        orderRepository.save(order);

        return orderDto;
    }
}
