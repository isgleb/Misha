package com.bunch_of_keys.bunch.dto;

import com.bunch_of_keys.bunch.domain.CleaningService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
public class PositionDto {

    private long id;
    ServiceDto serviceDto;
    int quantity;
    int totalPrice;
}
