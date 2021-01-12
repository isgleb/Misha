package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.Invoice;
import com.bunch_of_keys.bunch.domain.bills.InvoiceRepository;
import com.bunch_of_keys.bunch.domain.contragents.StuffRepository;
import com.bunch_of_keys.bunch.domain.documents.Cost;
import com.bunch_of_keys.bunch.domain.documents.CostRepository;
import com.bunch_of_keys.bunch.dto.CostTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostService {

    @Autowired
    CostRepository costRepository;

    @Autowired
    StuffRepository stuffRepository;

    @Autowired
    InvoiceRepository invoiceRepository;


    public List<CostTableDto> getCostsForTable() {

        List<CostTableDto> costTableDtos = new ArrayList<>();

        List<Cost> costs = costRepository.findAll();

        for (Cost cost: costs) {

            Invoice theInvoice = invoiceRepository.getByInvoiceRelatedDocument_id(cost.getId()).get(0);
            CostTableDto costTableDto = new CostTableDto();

            costTableDto.setId(cost.getId());
            costTableDto.setDate(cost.getDate());
            costTableDto.setContragent(theInvoice.getStuff().getName());
            costTableDto.setSum(theInvoice.getSum());
//            costTableDto.setCostTypes(theInvoice.setInvoicePositionSet(););

            costTableDtos.add(costTableDto);
        }

        return costTableDtos;
    }
}
