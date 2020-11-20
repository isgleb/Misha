package com.bunch_of_keys.bunch.domain;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDaoRepository extends CrudRepository<OrderDao, Long> {

//    List<OrderDao> findByTag(String tag);

}