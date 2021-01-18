package com.bunch_of_keys.bunch.domain.contragents;

import com.bunch_of_keys.bunch.domain.contragents.Contragent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EnableAutoConfiguration
@Table(name = "stuff")
public class Stuff extends InvoiceRelatedContragent {


    private String name;
    private String surname;
    private String email;
    private String telephone;
    private StuffStatus stuffStatus;
}

