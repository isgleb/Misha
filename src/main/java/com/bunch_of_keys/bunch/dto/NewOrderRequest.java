package com.bunch_of_keys.bunch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewOrderRequest {

    private long id;
    private String status;
    private Long customerID;
    private int cleaningServicesID;
    private String address;
    String dateReceived;
    String dateTimeOrder;
    int totalPrice;
}





