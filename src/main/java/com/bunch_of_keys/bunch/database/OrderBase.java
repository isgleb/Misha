package com.bunch_of_keys.bunch.database;

import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.apache.naming.factory.MailSessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderBase {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 0;

    private Map<Long, OrderDao> orders = new HashMap<>();

    public void addOrder(OrderDao newOrder) {
        id+=1;
        this.orders.put(id, newOrder);
    }


    public Map<Long, OrderDao> getOrders() {

        SessionFactory







        return orders;
    }


    public void deleteOrder(Long id) {
        orders.remove(id);
    }

    public void changeOrder(Long id, OrderDao order) {
        orders.put(id, order);
    }
}
