package com.bunch_of_keys.bunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StuffDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String stuffStatus;
}
