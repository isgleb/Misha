package com.bunch_of_keys.bunch.dto;

import com.bunch_of_keys.bunch.domain.CleaningService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private long id;
    ServiceDto serviceDto;
    int quantity;
    int totalPrice;

    public PositionDto(ServiceDto serviceDto, int quantity, int totalPrice) {
        this.serviceDto = serviceDto;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
