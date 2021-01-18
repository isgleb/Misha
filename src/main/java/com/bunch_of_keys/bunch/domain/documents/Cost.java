package com.bunch_of_keys.bunch.domain.documents;


import com.bunch_of_keys.bunch.domain.bills.Invoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cost")
public class Cost extends InvoiceRelatedDocument {


    private Date date;

}
