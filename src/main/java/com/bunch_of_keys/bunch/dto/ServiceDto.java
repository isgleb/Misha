package com.bunch_of_keys.bunch.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {

    long id;
    String serviceType;
    String priceModel;
    int price;
}
