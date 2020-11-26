package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.Customer;
import com.bunch_of_keys.bunch.domain.CustomerRepository;
import com.bunch_of_keys.bunch.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(CustomerDto customerDto) {

        Customer customer = new Customer(
                customerDto.getName(),
                customerDto.getSurname(),
                customerDto.getEmail(),
                customerDto.getTelephone()
        );
        customerRepository.save(customer);

    }

    public List<CustomerDto> getCustomers () {
        Iterable<Customer> allCustomers = customerRepository.findAll();
        List<CustomerDto> respCustomers = new ArrayList<>();
        for (Customer customer : allCustomers) {
            respCustomers.add(new CustomerDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getSurname(),
                    customer.getEmail(),
                    customer.getTelephone()
            ));
        }

        return respCustomers;
    }

    public CustomerDto getTheCustomer (Long orderId) {
        Customer theCustomer = customerRepository.findByOrders_id(orderId);
        CustomerDto theCustomerDto = new CustomerDto(theCustomer.getId(),
                                                    theCustomer.getName(),
                                                    theCustomer.getSurname(),
                                                    theCustomer.getEmail(),
                                                    theCustomer.getTelephone()
                                                    );
        return theCustomerDto;
    }



    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto editCustomer(CustomerDto customerDto) {

        Customer customerDao = new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getSurname(),
                customerDto.getEmail(),
                customerDto.getTelephone()
        );

        customerRepository.save(customerDao);

        return customerDto;
    }
}
