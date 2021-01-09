package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.Invoice;
import com.bunch_of_keys.bunch.domain.contragents.Customer;
import com.bunch_of_keys.bunch.domain.contragents.CustomerRepository;
import com.bunch_of_keys.bunch.domain.documents.*;
import com.bunch_of_keys.bunch.dto.AddressDto;
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
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;

    public OrderDto getTheOrder(long orderId){

        OrderDto orderDto = new OrderDto();
        Order order = orderRepository.getOne(orderId);

        orderDto.setId(order.getId());
        orderDto.setAddressDto(AddressDto.dtoFromAddress(order.getAddress()));
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
        order.setAddress(AddressDto.addressFromDto(orderDto.getAddressDto()));
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

    public void changeStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.getOne(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    public void changeAddress(Long orderId, Address address) {

        Address orderAddress = addressRepository.findByOrder_id(orderId);
        orderAddress = address;
        addressRepository.save(orderAddress);

    }

    public void changeMeters(Long orderId, Integer meters) {
        Order order = orderRepository.getOne(orderId);
        order.setMeters(meters);
        orderRepository.save(order);
    }
}
