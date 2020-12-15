package com.bunch_of_keys.bunch.domain.documents;


import com.bunch_of_keys.bunch.domain.bills.Invoice;
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
@Table(name = "cost")
public class Cost extends InvoiceRelatedDocument {





//    @OneToOne(mappedBy = "invoice_related_document_id", cascade = CascadeType.ALL)
//    Invoice invoice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="invoice_related_document_id")
    Invoice invoice;

}
