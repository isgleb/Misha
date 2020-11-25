package com.bunch_of_keys.bunch.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@EnableAutoConfiguration
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String status;
    private Long customerID;
    private int cleaningServicesID;
    private String address;
    String dateReceived;
    String dateTimeOrder;
    int totalPrice;

    public Order(Long id, String status, Long customerID, int cleaningServicesID, String address, String dateReceived, String datetimeOrder, int totalPrice) {
        this.id = id;
        this.status = status;
        this.customerID = customerID;
        this.cleaningServicesID = cleaningServicesID;
        this.address = address;
        this.dateReceived = dateReceived;
        this.dateTimeOrder = datetimeOrder;
        this.totalPrice = totalPrice;
    }

    public Order(String status, Long customerID, int cleaningServicesID, String address, String dateReceived, String datetimeOrder, int totalPrice) {
        this.status = status;
        this.customerID = customerID;
        this.cleaningServicesID = cleaningServicesID;
        this.address = address;
        this.dateReceived = dateReceived;
        this.dateTimeOrder = datetimeOrder;
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "OrderDao{" +
                "status='" + status + '\'' +
                ", customerID=" + customerID +
                ", cleaningServicesID=" + cleaningServicesID +
                ", address='" + address + '\'' +
                ", dateReceived='" + dateReceived + '\'' +
                ", datetimeOrder='" + dateTimeOrder + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
