package com.bunch_of_keys.bunch.domain.bills;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> getByInvoiceRelatedDocument_id(Long IrdId);

}
