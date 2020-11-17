package com.bunch_of_keys.bunch.database;

import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
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
    private List<OrderDao> orders = new ArrayList<>();

    public void addOrder(OrderDao newOrder) {

        this.orders.add(newOrder);
    }

    public List<OrderDao> getOrders() {
        return orders;
    }


    public void deleteOrder(Long id) {

        orders.remove();
    }
}
