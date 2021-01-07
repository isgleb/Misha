package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.Invoice;
import com.bunch_of_keys.bunch.domain.contragents.Customer;
import com.bunch_of_keys.bunch.domain.contragents.CustomerRepository;
import com.bunch_of_keys.bunch.domain.documents.Order;
import com.bunch_of_keys.bunch.domain.documents.OrderRepository;
import com.bunch_of_keys.bunch.domain.documents.OrderStatus;
import com.bunch_of_keys.bunch.dto.CustomerDto;
import com.bunch_of_keys.bunch.dto.OrderDto;
import com.bunch_of_keys.bunch.dto.TableOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    public OrderDto getTheOrder(long orderId){

        OrderDto orderDto = new OrderDto();
        Order order = orderRepository.getOne(orderId);

        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());

        orderDto.setCustomerDto(customerService.getTheCustomer(orderId));
        orderDto.setDate(order.getDate());
        orderDto.setMeters(order.getMeters());
        orderDto.setStatus(order.getStatus().toString());

        return orderDto;
    }




    public List<TableOrderDto> getOrders () {

        Iterable<Order> orders = orderRepository.getOrdersForTable();
        List<TableOrderDto> ordersResponse = new ArrayList<>();

        for (Order order : orders) {
            TableOrderDto tableOrderDto = new TableOrderDto();

            tableOrderDto.setId(order.getId());

            switch (order.getStatus()) {
                case accepted:
                    tableOrderDto.setStatus("Принят");
                    break;
                case done:
                    tableOrderDto.setStatus("Выполнен");
                    break;
                case canceled:
                    tableOrderDto.setStatus("Отменен");
                    break;
            }

            tableOrderDto.setDate(order.getDate());
            tableOrderDto.setMeters(order.getMeters());

            tableOrderDto.setCustomerNameAndTelephone(order.getCustomer().getName() +
                                                        ", " +
                                                        order.getCustomer().getTelephone());

            StringBuilder stringBuilder = new StringBuilder();
            Set<Invoice> invoiceSet = order.getInvoice();

            for (Invoice invoice: invoiceSet) {
                stringBuilder.append(invoice.getStuff().getName() + ", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

            tableOrderDto.setStuff(stringBuilder.toString());

            ordersResponse.add(tableOrderDto);
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

    public OrderDto createNewOrder(OrderDto orderDto) {



        Order order = new Order();
        Customer customer = customerRepository.getOne(orderDto.getCustomerDto().getId());
        order.setCustomer(customer);

        switch (orderDto.getStatus()) {
            case "accepted":
                order.setStatus(OrderStatus.accepted);
                break;
            case "canceled":
                order.setStatus(OrderStatus.canceled);
                break;
            case "done":
                order.setStatus(OrderStatus.done);
                break;
        }
        order.setAddress(orderDto.getAddress());
        order.setDate(orderDto.getDate());
        order.setMeters(orderDto.getMeters());

        orderRepository.save(order);

        orderDto.setId(order.getId());

        return orderDto;
    }

    public void changeDate(Long orderId, Date date) {
        Order order = orderRepository.getOne(orderId);
        order.setDate(date);
        orderRepository.save(order);
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
