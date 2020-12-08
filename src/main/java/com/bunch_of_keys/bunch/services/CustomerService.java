package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.contragents.Customer;
import com.bunch_of_keys.bunch.domain.contragents.CustomerRepository;
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

        Customer customer = new Customer();

        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setEmail(customerDto.getEmail());
        customer.setTelephone(customerDto.getTelephone());

        customerRepository.save(customer);

    }

    public List<CustomerDto> getCustomers () {
        Iterable<Customer> allCustomers = customerRepository.findAll();
        List<CustomerDto> respCustomers = new ArrayList<>();
        for (Customer customer : allCustomers) {

            CustomerDto customerDto = new CustomerDto();

            customerDto.setId(customer.getId());
            customerDto.setName(customer.getName());
            customerDto.setSurname(customer.getSurname());
            customerDto.setEmail(customer.getEmail());
            customerDto.setTelephone(customer.getTelephone());

            respCustomers.add(customerDto);
        }

        return respCustomers;
    }

    public CustomerDto getTheCustomer (Long orderId) {
        Customer theCustomer = customerRepository.findByOrders_id(orderId);

        CustomerDto theCustomerDto = new CustomerDto();

        theCustomerDto.setId(theCustomer.getId());
        theCustomerDto.setName(theCustomer.getName());
        theCustomerDto.setSurname(theCustomer.getSurname());
        theCustomerDto.setEmail(theCustomer.getEmail());
        theCustomerDto.setTelephone(theCustomer.getTelephone());

        return theCustomerDto;
    }



    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto editCustomer(CustomerDto customerDto) {
        long id = customerDto.getId();
        Customer customer = customerRepository.getOne(id);
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setEmail(customerDto.getEmail());
        customer.setTelephone(customerDto.getTelephone());
        customerRepository.save(customer);

        return customerDto;
    }
}
