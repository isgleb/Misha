package com.bunch_of_keys.bunch.dto;

import com.bunch_of_keys.bunch.domain.contragents.Stuff;
import com.bunch_of_keys.bunch.domain.documents.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {

    private long id;
    Long stuffId;

    //            later it will be Document
    Long orderId;
    int sum;

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "id=" + id +
                ", stuffId=" + stuffId +
                ", orderId=" + orderId +
                ", sum=" + sum +
                '}';
    }
}
