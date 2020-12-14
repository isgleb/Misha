package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.bills.CostType;
import com.bunch_of_keys.bunch.dto.CostDto;
import com.bunch_of_keys.bunch.dto.CostTypeDto;
import com.bunch_of_keys.bunch.dto.ServiceDto;
import com.bunch_of_keys.bunch.services.CleaningServService;
import com.bunch_of_keys.bunch.services.CostService;
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
    public ResponseEntity getCostTypes () {

        List<CostTypeDto> costTypeDtos = costTypeService.getCostTypes();

        return new ResponseEntity(costTypeDtos, HttpStatus.OK);
    }

    @PostMapping("/costs-types/request")
    public ResponseEntity newCostType (@RequestBody CostTypeDto costTypeDto) {
        costTypeService.addCostType(costTypeDto);
        return new ResponseEntity(costTypeDto, HttpStatus.OK);
    }

    @DeleteMapping("/costs-types/request")
    public void deleteCOstType (@RequestParam Long id) { // работает с postman
        costTypeService.deleteCostType(id);
    }

    @PutMapping("/costs-types/request")
    public ResponseEntity editCostType (@RequestBody CostTypeDto costTypeDto) {
        return new ResponseEntity(costTypeService.editCostType(costTypeDto), HttpStatus.OK);
    }


    @Autowired
    private CostService costService;

    @GetMapping("/costs/request")
    public ResponseEntity getCosts () {

        List<CostDto> costDtos = costService.getCosts();

        return new ResponseEntity(costDtos, HttpStatus.OK);
    }

    @PostMapping("/costs/request")
    public ResponseEntity newCost (@RequestBody CostDto costDto) {
        costService.addCostType(costDto);
        return new ResponseEntity(costDto, HttpStatus.OK);
    }

    @DeleteMapping("/costs/request")
    public void deleteCost (@RequestParam Long id) { // работает с postman
        costService.deleteCost(id);
    }

    @PutMapping("/costs/request")
    public ResponseEntity editCost (@RequestBody CostDto costDto) {
        return new ResponseEntity(costService.editCost(costDto), HttpStatus.OK);
    }


}
