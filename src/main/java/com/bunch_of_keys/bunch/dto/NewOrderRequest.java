package com.bunch_of_keys.bunch.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrderRequest {

    private long id;
    private String status;
    private int customerID;
    private int cleaningServicesID;
    private String address;
    String dateReceived;
    String dateTimeOrder;
    int totalPrice;

    public NewOrderRequest(long id, String status, int customerID, int cleaningServicesID, String address, String dateReceived, String dateTimeOrder, int totalPrice) {
        this.id = id;
        this.status = status;
        this.customerID = customerID;
        this.cleaningServicesID = cleaningServicesID;
        this.address = address;
        this.dateReceived = dateReceived;
        this.dateTimeOrder = dateTimeOrder;
        this.totalPrice = totalPrice;

    }
}





