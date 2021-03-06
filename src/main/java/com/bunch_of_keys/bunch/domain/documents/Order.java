package com.bunch_of_keys.bunch.domain.documents;

//import com.bunch_of_keys.bunch.domain.bills.Cost;
import com.bunch_of_keys.bunch.domain.contragents.Customer;
import com.bunch_of_keys.bunch.domain.bills.Position;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@DiscriminatorValue("order")
@EnableAutoConfiguration
public class Order extends InvoiceRelatedDocument{

    private OrderStatus status;

//    не удаляется при удалении заказа
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private int meters;

//    String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<Position> positions;




//    @OneToMany(mappedBy = "cost", cascade = CascadeType.ALL)
//    List<Cost> invoicePositions;


    @Override
    public String toString() {
        return "Order{" +
                "status=" + status +
                ", address=" + address +
                ", date=" + date +
                ", meters=" + meters +
                ", customer=" + customer +
                ", positions=" + positions +
                '}';
    }
}
