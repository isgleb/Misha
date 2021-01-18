package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.CostDto;
import com.bunch_of_keys.bunch.dto.CostTableDto;
import com.bunch_of_keys.bunch.services.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CostsController {

    @Autowired
    CostService costService;

    @GetMapping("/costs-table")
    public ResponseEntity getCosts() {
        List<CostTableDto> costTableDtos = costService.getCostsForTable();
        return new ResponseEntity(costTableDtos, HttpStatus.OK);
    }

    @GetMapping("/the-cost")
    public ResponseEntity getCosts(@RequestParam Long costId) {
        CostDto costDto = costService.getTheCost(costId);
        return new ResponseEntity(costDto, HttpStatus.OK);
    }

    @PostMapping("/new-cost")
    public ResponseEntity newCost(@RequestBody CostDto costDto) {
        CostDto returnCostDto = costService.newCost(costDto);
        return new ResponseEntity(returnCostDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/cost/delete")
    public ResponseEntity deleteCost(@RequestParam Long id) {
        costService.deleteCost(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
