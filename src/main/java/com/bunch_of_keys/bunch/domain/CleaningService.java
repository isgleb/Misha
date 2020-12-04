package com.bunch_of_keys.bunch.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cleaning_services")
public class CleaningService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String serviceType;
    private String priceModel;
    private int price;

    public CleaningService(String serviceType, String priceModel, int price) {
        this.serviceType = serviceType;
        this.priceModel = priceModel;
        this.price = price;
    }
}
