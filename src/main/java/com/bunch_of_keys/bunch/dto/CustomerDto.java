package com.bunch_of_keys.bunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
}
