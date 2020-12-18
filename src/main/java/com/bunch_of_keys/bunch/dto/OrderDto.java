package com.bunch_of_keys.bunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private long id;
    private String status;
    private long customerId;



    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", customerID=" + customerId +
                '}';
    }
}





