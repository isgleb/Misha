package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.bills.CostType;
import com.bunch_of_keys.bunch.dto.CostTypeDto;
import com.bunch_of_keys.bunch.dto.ServiceDto;
import com.bunch_of_keys.bunch.services.CleaningServService;
import com.bunch_of_keys.bunch.services.CostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CostController {

    @Autowired
    private CostTypeService costTypeService;

    @GetMapping("/costs-types/request")
    public ResponseEntity getServices () {

        List<CostTypeDto> costTypeDtos = costTypeService.getCostTypes();

        return new ResponseEntity(costTypeDtos, HttpStatus.OK);
    }

    @PostMapping("/costs-types/request")
    public ResponseEntity newService (@RequestBody CostTypeDto costTypeDto) {
        costTypeService.addCostType(costTypeDto);
        return new ResponseEntity(costTypeDto, HttpStatus.OK);
    }

    @DeleteMapping("/costs-types/request")
    public void deleteService (@RequestParam Long id) { // работает с postman
        costTypeService.deleteCostType(id);
    }

    @PutMapping("/costs-types/request")
    public ResponseEntity editService (@RequestBody CostTypeDto costTypeDto) {
        return new ResponseEntity(costTypeService.editCostType(costTypeDto), HttpStatus.OK);
    }
}
