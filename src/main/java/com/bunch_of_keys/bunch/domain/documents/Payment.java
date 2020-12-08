package com.bunch_of_keys.bunch.domain.documents;


import com.bunch_of_keys.bunch.domain.bills.Cost;
import com.bunch_of_keys.bunch.domain.contragents.Contragent;
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
public class Payment extends Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "cost", cascade = CascadeType.ALL)
    List<Cost> costs;

    int totalSum;

    Contragent contragent;
}
