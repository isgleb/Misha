package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.*;
import com.bunch_of_keys.bunch.dto.CostDto;
import com.bunch_of_keys.bunch.dto.CostTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostService {

    @Autowired
    CostRepository costRepository;

    @Autowired
    CostTypeRepository costTypeRepository;

    @Autowired
    InvoiceRepository invoiceRepository;


    public List<CostDto> getCosts (){
        List<Cost> costs = costRepository.findAll();
        List<CostDto> costDtos = new ArrayList<>();

        for (Cost cost: costs) {

            CostDto costDto = new CostDto();

            costDto.setId(cost.getId());
            costDto.setInvoiceID(cost.getInvoice().getId());
            costDto.setPrice(cost.getPrice());
            costDto.setCostTypeId(cost.getCostType().getId());
            costDto.setGood(cost.getGood());

            costDtos.add(costDto);
        }

        return costDtos;
    }


    public void addCostType (CostDto costDto){

        Cost cost = new Cost();

        cost.setId(costDto.getId());
        cost.setCostType(costTypeRepository.getOne(costDto.getCostTypeId()));
        cost.setPrice(costDto.getPrice());
        cost.setGood(costDto.getGood());
        cost.setInvoice(invoiceRepository.getOne(costDto.getInvoiceID()));


        costRepository.save(cost);
    }

    public void deleteCost (Long id) {
        costRepository.deleteById(id);
    }


    public CostDto editCost (CostDto costDto){
//        возможно объединить с методом по добавлению

        Cost cost = new Cost();

        cost.setId(costDto.getId());
        cost.setCostType(costTypeRepository.getOne(costDto.getCostTypeId()));
        cost.setPrice(costDto.getPrice());
        cost.setGood(costDto.getGood());
        cost.setInvoice(invoiceRepository.getOne(costDto.getInvoiceID()));


        costRepository.save(cost);

        return costDto;
    }
}
