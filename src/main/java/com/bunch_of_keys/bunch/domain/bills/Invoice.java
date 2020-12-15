package com.bunch_of_keys.bunch.domain.bills;


import com.bunch_of_keys.bunch.domain.contragents.Stuff;
import com.bunch_of_keys.bunch.domain.documents.InvoiceRelatedDocument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Set;
import javax.persistence.CascadeType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


//    later it will be Contragent contragent;
    @ManyToOne
    Stuff stuff;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "invoice")
    Set<InvoicePosition> invoicePositionSet;


    @ManyToOne
    @JoinColumn(name="invoice_related_document_id", nullable=false)
    InvoiceRelatedDocument invoiceRelatedDocument;

    int sum;
}
