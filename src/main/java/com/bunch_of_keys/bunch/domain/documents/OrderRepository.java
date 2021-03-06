package com.bunch_of_keys.bunch.domain.documents;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join fetch o.customer")
    Iterable<Order> getOrdersForTable();
}