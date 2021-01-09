package com.bunch_of_keys.bunch.domain.documents;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String yandexAddress;

    private String entrance;
    private String level;
    private String accommodation;
    private String intercom;

    @OneToOne(mappedBy = "address")
    private Order order;





    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", yandexAddress='" + yandexAddress + '\'' +
                ", entrance='" + entrance + '\'' +
                ", level='" + level + '\'' +
                ", accommodation='" + accommodation + '\'' +
                ", intercom='" + intercom + '\'' +
                '}';
    }
}
