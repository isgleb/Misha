package com.bunch_of_keys.bunch.dto;

import com.bunch_of_keys.bunch.domain.contragents.Customer;
import com.bunch_of_keys.bunch.domain.documents.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long id;
    private String status;
    private CustomerDto customerDto;
    private AddressDto addressDto;
    private int meters;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX"

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;



//    @Override
//    public String toString() {
//        return "OrderDto{" +
//                "id=" + id +
//                ", status='" + status + '\'' +
//                ", customerId=" + customerId +
//                ", address=" + address +
//                ", meters=" + meters +
//                ", date=" + date +
//                '}';
//    }
}





