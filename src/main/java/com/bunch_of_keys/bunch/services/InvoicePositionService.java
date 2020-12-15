package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.*;
import com.bunch_of_keys.bunch.dto.InvoicePositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoicePositionService {

    @Autowired
    InvoicePositionRepository invoicePositionRepository;

    @Autowired
    CostTypeRepository costTypeRepository;

    @Autowired
    InvoiceRepository invoiceRepository;


    public List<InvoicePositionDto> getCosts (){
        List<InvoicePosition> invoicePositions = invoicePositionRepository.findAll();
        List<InvoicePositionDto> invoicePositionDtos = new ArrayList<>();

        for (InvoicePosition invoicePosition : invoicePositions) {

            InvoicePositionDto invoicePositionDto = new InvoicePositionDto();

            invoicePositionDto.setId(invoicePosition.getId());
            invoicePositionDto.setInvoiceID(invoicePosition.getInvoice().getId());
            invoicePositionDto.setPrice(invoicePosition.getPrice());
            invoicePositionDto.setCostTypeId(invoicePosition.getCostType().getId());
            invoicePositionDto.setGood(invoicePosition.getGood());

            invoicePositionDtos.add(invoicePositionDto);
        }

        return invoicePositionDtos;
    }


    public void addInvoicePosition (InvoicePositionDto invoicePositionDto){

        InvoicePosition invoicePosition = new InvoicePosition();

        invoicePosition.setId(invoicePositionDto.getId());
        invoicePosition.setCostType(costTypeRepository.getOne(invoicePositionDto.getCostTypeId()));
        invoicePosition.setPrice(invoicePositionDto.getPrice());
        invoicePosition.setGood(invoicePositionDto.getGood());
        invoicePosition.setInvoice(invoiceRepository.getOne(invoicePositionDto.getInvoiceID()));


        invoicePositionRepository.save(invoicePosition);
    }

    public void deleteInvoicePosition (Long id) {
        invoicePositionRepository.deleteById(id);
    }


    public InvoicePositionDto editInvoicePosition (InvoicePositionDto invoicePositionDto){
//        возможно объединить с методом по добавлению

        InvoicePosition invoicePosition = new InvoicePosition();

        invoicePosition.setId(invoicePositionDto.getId());
        invoicePosition.setCostType(costTypeRepository.getOne(invoicePositionDto.getCostTypeId()));
        invoicePosition.setPrice(invoicePositionDto.getPrice());
        invoicePosition.setGood(invoicePositionDto.getGood());
        invoicePosition.setInvoice(invoiceRepository.getOne(invoicePositionDto.getInvoiceID()));


        invoicePositionRepository.save(invoicePosition);

        return invoicePositionDto;
    }
}
