package com.bunch_of_keys.bunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private long id;
    long serviceId;
//    int quantity;
    int totalPrice;

}
