package com.bunch_of_keys.bunch.domain.documents;

//import com.bunch_of_keys.bunch.domain.bills.Cost;
import com.bunch_of_keys.bunch.domain.contragents.Customer;
import com.bunch_of_keys.bunch.domain.bills.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@EnableAutoConfiguration
public class Order extends Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<Position> positions;

//    @OneToMany(mappedBy = "cost", cascade = CascadeType.ALL)
//    List<Cost> costs;

}
