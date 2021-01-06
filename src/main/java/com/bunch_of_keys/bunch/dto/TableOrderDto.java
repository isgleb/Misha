package com.bunch_of_keys.bunch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableOrderDto {
    private long id;
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    int meters;

    private String customerNameAndTelephone;

    private String stuff;

}
