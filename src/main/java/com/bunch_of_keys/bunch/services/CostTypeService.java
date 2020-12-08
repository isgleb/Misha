package com.bunch_of_keys.bunch.services;


import com.bunch_of_keys.bunch.domain.bills.CostType;
import com.bunch_of_keys.bunch.domain.bills.CostTypeRepository;
import com.bunch_of_keys.bunch.dto.CostTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostTypeService {

    @Autowired
    CostTypeRepository costTypeRepository;


    public List<CostTypeDto> getCostTypes (){
        List<CostType> costTypes = costTypeRepository.findAll();
        List<CostTypeDto> costTypeDtos = new ArrayList<>();

        for (CostType costType: costTypes) {

            CostTypeDto costTypeDto = new CostTypeDto();

            costTypeDto.setId(costType.getId());
            costTypeDto.setCostType((costType.getCostType()));

            costTypeDtos.add(costTypeDto);
            }

        return costTypeDtos;
    }


    public void addCostType (CostTypeDto costTypeDto){

        CostType costType = new CostType();

        costType.setId(costTypeDto.getId());
        costType.setCostType(costTypeDto.getCostType());

        costTypeRepository.save(costType);
    }

    public void deleteCostType (Long id) {
        costTypeRepository.deleteById(id);
    }


    public CostTypeDto editCostType (CostTypeDto costTypeDto){
        long id = costTypeDto.getId();
        CostType costType = costTypeRepository.getOne(id);

        costType.setId(costTypeDto.getId());
        costType.setCostType(costTypeDto.getCostType());

        costTypeRepository.save(costType);
        return costTypeDto;
    }
}
