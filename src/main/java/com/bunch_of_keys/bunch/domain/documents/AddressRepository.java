package com.bunch_of_keys.bunch.domain.documents;

import com.bunch_of_keys.bunch.domain.contragents.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByOrder_id(Long orderId);
}
