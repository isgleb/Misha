package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.*;
import com.bunch_of_keys.bunch.domain.contragents.Stuff;
import com.bunch_of_keys.bunch.domain.contragents.StuffRepository;
import com.bunch_of_keys.bunch.domain.documents.InvoiceRelatedDocument;
import com.bunch_of_keys.bunch.domain.documents.IrdRepository;
import com.bunch_of_keys.bunch.domain.documents.OrderRepository;
import com.bunch_of_keys.bunch.dto.InvoiceDto;
import com.bunch_of_keys.bunch.dto.PositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoicePositionRepository invoicePositionRepository;

    @Autowired
    IrdRepository irdRepository;

    @Autowired
    StuffRepository stuffRepository;


    public List<InvoiceDto> getInvoicesByOrder(Long IrdId) {

        List<Invoice> invoices = invoiceRepository.getByInvoiceRelatedDocument_id(IrdId);
        List<InvoiceDto> invoiceDtos = new ArrayList<>();

        for (Invoice invoice: invoices) {

            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setId(invoice.getId());
            invoiceDto.setStuffId(invoice.getStuff().getId());
            invoiceDto.setInvoiceRelatedDocumentId(IrdId);
            invoiceDto.setSum(invoice.getSum());

            invoiceDtos.add(invoiceDto);
        }

        return invoiceDtos;
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }


    @Autowired
    InvoicePositionService invoicePositionService;

    public void newOrderInvoice (InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();

        invoice.setInvoiceRelatedDocument(irdRepository.getOne(invoiceDto.getInvoiceRelatedDocumentId()));
        invoice.setStuff(stuffRepository.getOne(invoiceDto.getStuffId()));
        invoice.setSum(invoiceDto.getSum());

        invoiceRepository.save(invoice);

        invoicePositionService.addInvoicePositionFromOrderInvoice(invoice.getSum(), invoice);
    }

    public InvoiceDto editInvoice(InvoiceDto invoiceDto) {

        Invoice theInvoice = invoiceRepository.getOne(invoiceDto.getId());

        theInvoice.setInvoiceRelatedDocument(irdRepository.getOne(invoiceDto.getInvoiceRelatedDocumentId()));
        theInvoice.setStuff(stuffRepository.getOne(invoiceDto.getStuffId()));
        theInvoice.setSum(invoiceDto.getSum());

        invoiceRepository.save(theInvoice);

        invoicePositionService.editInvoicePositionFromOrderInvoice(invoiceDto.getSum(), theInvoice);

        return invoiceDto;
    }
}
