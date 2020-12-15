package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.CostTypeDto;
import com.bunch_of_keys.bunch.services.CostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CostTypesController {

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
    public void deleteCostType (@RequestParam Long id) { // работает с postman
        costTypeService.deleteCostType(id);
    }

    @PutMapping("/costs-types/request")
    public ResponseEntity editCostType (@RequestBody CostTypeDto costTypeDto) {
        return new ResponseEntity(costTypeService.editCostType(costTypeDto), HttpStatus.OK);
    }
}
