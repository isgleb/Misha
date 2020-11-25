package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.Customer;
import com.bunch_of_keys.bunch.domain.CustomerRepository;
import com.bunch_of_keys.bunch.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Customer> getCustomers () {
        Iterable<Customer> allCustomers = customerRepository.findAll();
        return allCustomers;
    }

    public CustomerDto getTheCustomer (Long customerId) {
        Customer theCustomer = customerRepository.findById(customerId).get();
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
