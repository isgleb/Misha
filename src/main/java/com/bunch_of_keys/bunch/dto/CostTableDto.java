package com.bunch_of_keys.bunch.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostTableDto {

    private long id;
    private Date date;
    private String contragent;
    private int sum;
//    private String costTypes;

}
