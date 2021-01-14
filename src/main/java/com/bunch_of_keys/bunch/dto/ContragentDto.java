package com.bunch_of_keys.bunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContragentDto {

    private Long id;
    private String name;

    @Override
    public String toString() {
        return "ContragentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
