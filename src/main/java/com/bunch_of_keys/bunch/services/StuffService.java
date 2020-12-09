package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.contragents.Stuff;
import com.bunch_of_keys.bunch.domain.contragents.StuffRepository;
import com.bunch_of_keys.bunch.dto.StuffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StuffService {

    @Autowired
    private StuffRepository stuffRepository;

    public void addStuff(StuffDto stuffDto) {

        Stuff stuff = new Stuff();

        stuff.setName(stuffDto.getName());
        stuff.setSurname(stuffDto.getSurname());
        stuff.setEmail(stuffDto.getEmail());
        stuff.setTelephone(stuffDto.getTelephone());

        stuffRepository.save(stuff);

    }

    public List<StuffDto> getStuff () {
        Iterable<Stuff> allCustomers = stuffRepository.findAll();
        List<StuffDto> respCustomers = new ArrayList<>();
        for (Stuff customer : allCustomers) {

            StuffDto customerDto = new StuffDto();

            customerDto.setId(customer.getId());
            customerDto.setName(customer.getName());
            customerDto.setSurname(customer.getSurname());
            customerDto.setEmail(customer.getEmail());
            customerDto.setTelephone(customer.getTelephone());

            respCustomers.add(customerDto);
        }

        return respCustomers;
    }

//    public StuffDto getTheStuff (Long orderId) {
//        Stuff theCustomer = stuffRepository.findByOrders_id(orderId);
//
//        StuffDto theCustomerDto = new StuffDto();
//
//        theCustomerDto.setId(theCustomer.getId());
//        theCustomerDto.setName(theCustomer.getName());
//        theCustomerDto.setSurname(theCustomer.getSurname());
//        theCustomerDto.setEmail(theCustomer.getEmail());
//        theCustomerDto.setTelephone(theCustomer.getTelephone());
//
//        return theCustomerDto;
//    }


    public void deleteStuff(Long id) {
        stuffRepository.deleteById(id);
    }

    public StuffDto editStuff(StuffDto customerDto) {
        long id = customerDto.getId();
        Stuff customer = stuffRepository.getOne(id);
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setEmail(customerDto.getEmail());
        customer.setTelephone(customerDto.getTelephone());
        stuffRepository.save(customer);

        return customerDto;
    }
}
