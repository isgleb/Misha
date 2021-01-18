package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.contragents.Contragent;
import com.bunch_of_keys.bunch.domain.contragents.ContragentRepository;
import com.bunch_of_keys.bunch.dto.ContragentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContragentController {

    @Autowired
    ContragentRepository contragentRepository;

    @GetMapping("/contragents/get")
    public ResponseEntity getContragents() {
        List<Contragent> contragents = contragentRepository.findAll();
        List<ContragentDto> contragentDtos = new ArrayList<>();
        contragents.forEach(contragent -> contragentDtos.add(new ContragentDto(contragent.getId(), contragent.getName())));
        return new ResponseEntity(contragentDtos, HttpStatus.OK);
    }

    @PostMapping("/contragents/new")
    public ResponseEntity addContragent(@RequestBody ContragentDto contragentDto) {
//        System.out.println(contragentDto);
        Contragent contragent = new Contragent();
        contragent.setName(contragentDto.getName());
        contragentRepository.save(contragent);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/contragents/edit")
    public ResponseEntity editContragent(@RequestBody ContragentDto contragentDto) {

        Contragent contragent  = contragentRepository.getOne(contragentDto.getId());
        contragent.setName(contragentDto.getName());
        contragentRepository.save(contragent);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/contragents/delete")
    public ResponseEntity deleteContragent(@RequestParam Long id) {
        contragentRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
