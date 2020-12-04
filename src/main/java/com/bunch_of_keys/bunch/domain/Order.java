package com.bunch_of_keys.bunch.domain;

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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;

//
//    @OneToMany
////  должно быть каскадное удаление
//    List<Position> positions;


    public Order(Long id, String status, Customer customer) {
        this.id = id;
        this.status = status;
        this.customer = customer;
    }

    public Order(String status, Customer customer) {
        this.status = status;
        this.customer = customer;
    }

}
