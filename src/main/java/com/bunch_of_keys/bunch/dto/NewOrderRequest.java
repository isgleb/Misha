package com.bunch_of_keys.bunch.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrderRequest {

//    в заказе вложенный Enum статусов заказов
//    этот класс и является одним из классов типа DTO
//    переименовать в OrderDTO
//    объект состоит из DAO????

    private long id;
    private String status;
    private int customerID;
    private int cleanigServicesID; // one to many connection/  to test - only one
    private String address;
    String dateRecieved;
    String dateTimeOrder;
    int totalPrice;

    public NewOrderRequest(long id, String status, int customerID, int cleanigServicesID, String address, String dateRecieved, String dateTimeOrder, int totalPrice) {
        this.id = id;
        this.status = status;
        this.customerID = customerID;
        this.cleanigServicesID = cleanigServicesID;
        this.address = address;
        this.dateRecieved = dateRecieved;
        this.dateTimeOrder = dateTimeOrder;
        this.totalPrice = totalPrice;

    }
}





