package com.bunch_of_keys.bunch.services;

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

    public List<CostTableDto> getCosts() {

        List<CostTableDto> costTableDtos = new ArrayList<>();

        List<Cost> costs = costRepository.findAll();

        for (Cost cost: costs) {
            CostTableDto costTableDto = new CostTableDto();
            costTableDto.setId(cost.getId());
//            costTableDto.setContragent(cost.getInvoice().getStuff().getName());
        }




        return costTableDtos;
    }
}
