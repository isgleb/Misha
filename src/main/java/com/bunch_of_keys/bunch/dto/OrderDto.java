package com.bunch_of_keys.bunch.dto;

import com.bunch_of_keys.bunch.domain.documents.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private long id;
    private String status;
    private long customerId;
    private Address address;
    private int meters;
    private Date date;


    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", customerId=" + customerId +
                ", address=" + address +
                ", meters=" + meters +
                ", date=" + date +
                '}';
    }
}





