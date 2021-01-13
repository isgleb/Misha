package com.bunch_of_keys.bunch.domain.bills;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoicePositionRepository extends JpaRepository<InvoicePosition, Long> {


    InvoicePosition findByInvoice(Invoice invoice);

    List<InvoicePosition> findByInvoice_id(long id);
}
