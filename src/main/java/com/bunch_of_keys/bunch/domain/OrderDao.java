package com.bunch_of_keys.bunch.domain;

public class OrderDao {

    private int id;
    private String customer;
    private int price;

    public OrderDao(int id, String customer, int price) {
        this.id = id;
        this.customer = customer;
        this.price = price;
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
