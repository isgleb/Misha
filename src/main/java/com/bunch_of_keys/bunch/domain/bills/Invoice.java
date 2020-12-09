package com.bunch_of_keys.bunch.domain.bills;

import com.bunch_of_keys.bunch.domain.contragents.Contragent;
import com.bunch_of_keys.bunch.domain.documents.Order;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    Contragent contragent;

    int sum;

    @ManyToOne
    Order order;


}
