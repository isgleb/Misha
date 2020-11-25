package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.CustomerDao;
import com.bunch_of_keys.bunch.domain.CustomerDaoRepository;
import com.bunch_of_keys.bunch.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerDaoRepository customerDaoRepository;

    public void addCustomer(CustomerDto customerDto) {

        CustomerDao customerDao = new CustomerDao(
                customerDto.getName(),
                customerDto.getSurname(),
                customerDto.getEmail(),
                customerDto.getTelephone()
        );
        customerDaoRepository.save(customerDao);

    }

    public Iterable<CustomerDao> getCustomers () {
        Iterable<CustomerDao> allCustomers = customerDaoRepository.findAll();
        return allCustomers;
    }

    public CustomerDto getTheCustomer (Long customerId) {
        CustomerDao theCustomer = customerDaoRepository.findById(customerId).get();
        CustomerDto theCustomerDto = new CustomerDto(theCustomer.getId(),
                                                    theCustomer.getName(),
                                                    theCustomer.getSurname(),
                                                    theCustomer.getEmail(),
                                                    theCustomer.getTelephone()
                                                    );
        return theCustomerDto;
    }



    public void deleteCustomer(Long id) {
        customerDaoRepository.deleteById(id);
    }

    public CustomerDto editCustomer(CustomerDto customerDto) {

        CustomerDao customerDao = new CustomerDao(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getSurname(),
                customerDto.getEmail(),
                customerDto.getTelephone()
        );

        customerDaoRepository.save(customerDao);

        return customerDto;
    }
}
