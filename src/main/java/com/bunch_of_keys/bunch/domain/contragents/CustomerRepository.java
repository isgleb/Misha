package com.bunch_of_keys.bunch.domain.contragents;


import com.bunch_of_keys.bunch.domain.contragents.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByOrders_id(Long orderId);
}
