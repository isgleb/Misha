package com.bunch_of_keys.bunch.domain.bills;

import com.bunch_of_keys.bunch.domain.bills.CostType;
import com.bunch_of_keys.bunch.domain.contragents.Contragent;
import com.bunch_of_keys.bunch.domain.documents.Document;
import com.bunch_of_keys.bunch.domain.documents.Order;

import javax.persistence.*;
import java.util.List;

public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cost_type_id", nullable=false)
    CostType costType;

    String comments;

    int totalSum;
    
//    @ManyToOne
//    @JoinColumn(name="document_id", nullable=false)
//    private Document document;

}
