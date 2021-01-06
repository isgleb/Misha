package com.bunch_of_keys.bunch.domain.documents;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    String yandexAddress;

    String entrance;
    String level;
    String accommodation;
    String intercom;


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
