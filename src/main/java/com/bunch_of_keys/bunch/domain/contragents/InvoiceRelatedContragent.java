package com.bunch_of_keys.bunch.domain.contragents;

import com.bunch_of_keys.bunch.domain.bills.Invoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InvoiceRelatedContragent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @OneToMany(mappedBy = "invoiceRelatedContragent")
    Set<Invoice> invoice;

    private String name;

}
