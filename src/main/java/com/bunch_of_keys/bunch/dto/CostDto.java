package com.bunch_of_keys.bunch.dto;

import com.bunch_of_keys.bunch.domain.bills.CostType;
import com.bunch_of_keys.bunch.domain.bills.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostDto {

    private long id;

    Long costTypeId;


    Long invoiceID;

    int price;

    String good;
}
