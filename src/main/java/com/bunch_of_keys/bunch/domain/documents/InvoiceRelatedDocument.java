package com.bunch_of_keys.bunch.domain.documents;

import com.bunch_of_keys.bunch.domain.bills.Invoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InvoiceRelatedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="invoice_related_document_id")
//    List<Invoice> invoice;




//    @ManyToMany
//    @JoinTable(name = “PublicationAuthor”, joinColumns = { @JoinColumn(name = “publicationId”, referencedColumnName = “id”) }, inverseJoinColumns = { @JoinColumn(name = “authorId”, referencedColumnName = “id”) })
//    private Set authors = new HashSet();

//
//    @Version
//    @Column(name = “version”)
//    private int version;

}
