package com.bunch_of_keys.bunch.domain.bills;

import com.bunch_of_keys.bunch.domain.bills.CleaningService;
import com.bunch_of_keys.bunch.domain.documents.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "positions")
@EnableAutoConfiguration
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable=false)
    CleaningService cleaningService;
    int totalPrice;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

}
