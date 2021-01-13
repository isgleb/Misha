package com.bunch_of_keys.bunch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CostDto {

    long id;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String comments;
}
