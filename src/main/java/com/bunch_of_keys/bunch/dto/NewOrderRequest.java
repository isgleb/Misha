package com.bunch_of_keys.bunch.dto;

public class NewOrderRequest {

//    в заказе вложенный Enum статусов заказов
//    этот класс и является одним из классов типа DTO
//    переименовать в OrderDTO
//    объект состоит из DAO????

    private int id;
    private String customer;
    private int price;
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}



