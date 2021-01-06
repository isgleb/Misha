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
    int level;
    String accommodation;
    String intercom;
}
