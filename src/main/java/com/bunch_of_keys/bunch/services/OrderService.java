package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.Order;
import com.bunch_of_keys.bunch.domain.OrderRepository;
import com.bunch_of_keys.bunch.dto.OrderDto;
import com.bunch_of_keys.bunch.dto.TableOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

//    public void newOrder(OrderDto orderDto) {
//
//        Order order = new Order(
//                                    orderDto.getStatus(),
//                                    orderDto.getCustomer()
//        );
//        orderRepository.save(order);
//
//    }

    public List<TableOrderDto> getOrders () {
//        Iterable<Order> orders = orderRepository.findAll();
        Iterable<Order> orders = orderRepository.getOrdersForTable();
        List<TableOrderDto> ordersResponse = new ArrayList<>();

        for (Order order : orders) {
            ordersResponse.add(new TableOrderDto(
                    order.getId(),
                    order.getStatus(),
                    order.getCustomer().getName(),
                    order.getCustomer().getSurname(),
                    order.getCustomer().getTelephone()));
        }
        return ordersResponse;
    }

//    public Long getCustomerId (Long orderId) {
//        Order theOrder= orderRepository.findById(orderId).get();
//        Long customerId = theOrder.getCustomerID();
//        return customerId;
//    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

//    public OrderDto editOrder(OrderDto orderDto) {
//
//        Order order = new Order(
//                orderDto.getStatus(),
//                orderDto.getCustomerID(),
//                orderDto.getCleaningServicesID(),
//                orderDto.getAddress(),
//                orderDto.getDateReceived(),
//                orderDto.getDateTimeOrder(),
//                orderDto.getTotalPrice()
//        );
//
//        orderRepository.save(order);
//
//        return orderDto;
//    }
}
