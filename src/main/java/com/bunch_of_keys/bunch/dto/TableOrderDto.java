package com.bunch_of_keys.bunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableOrderDto {
    private long id;
    private String status;
    private String customerName;
    private String customerSurname;
    private String customerTelephone;
}
