package com.bunch_of_keys.bunch.dto;

import com.bunch_of_keys.bunch.domain.CleaningService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private long id;
    long serviceId;
    int quantity;
    int totalPrice;

    public PositionDto(long serviceId, int quantity, int totalPrice) {
        this.serviceId = serviceId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
