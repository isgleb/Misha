package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.Customer;
import com.bunch_of_keys.bunch.domain.CustomerRepository;
import com.bunch_of_keys.bunch.domain.Order;
import com.bunch_of_keys.bunch.domain.OrderRepository;
import com.bunch_of_keys.bunch.dto.CustomerDto;
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

    @Autowired
    private CustomerRepository customerRepository;


    public List<TableOrderDto> getOrders () {

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

    public void changeCustomer(Long orderId, Long customerId) {

        Customer anotherCustomer = customerRepository.getOne(customerId);
        Order orderToUpdate = orderRepository.getOne(orderId);
        orderToUpdate.setCustomer(anotherCustomer);
        orderRepository.save(orderToUpdate);
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
