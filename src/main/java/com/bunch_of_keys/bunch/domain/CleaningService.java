package com.bunch_of_keys.bunch.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cleaning_services")
public class CleaningService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String serviceType;
    private String priceModel;
    private int price;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy= "cleaningService")
//    private Set<Position> positions;



    public CleaningService(String serviceType, String priceModel, int price) {
        this.serviceType = serviceType;
        this.priceModel = priceModel;
        this.price = price;
    }


    @Override
    public String toString() {
        return "CleaningService{" +
                "id=" + id +
                ", serviceType='" + serviceType + '\'' +
                ", priceModel='" + priceModel + '\'' +
                ", price=" + price +
                '}';
    }
}
