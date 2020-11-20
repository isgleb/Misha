package com.bunch_of_keys.bunch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Entity
public class OrderDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String status;
    private int customerID;
    private int cleanigServicesID; // one to many connection/  to test - only one
    private String address;
    String dateRecieved;
    String datetimeOrder;
    int totalPrice;

    public OrderDao() {
    }

    public OrderDao(String status, int customerID, int cleanigServicesID, String address, String dateRecieved, String datetimeOrder, int totalPrice) {
        this.status = status;
        this.customerID = customerID;
        this.cleanigServicesID = cleanigServicesID;
        this.address = address;
        this.dateRecieved = dateRecieved;
        this.datetimeOrder = datetimeOrder;
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "OrderDao{" +
                "status='" + status + '\'' +
                ", customerID=" + customerID +
                ", cleanigServicesID=" + cleanigServicesID +
                ", address='" + address + '\'' +
                ", dateRecieved='" + dateRecieved + '\'' +
                ", datetimeOrder='" + datetimeOrder + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
