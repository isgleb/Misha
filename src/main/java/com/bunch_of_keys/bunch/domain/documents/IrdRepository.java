package com.bunch_of_keys.bunch.domain.documents;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IrdRepository extends JpaRepository<InvoiceRelatedDocument, Long> {
}
