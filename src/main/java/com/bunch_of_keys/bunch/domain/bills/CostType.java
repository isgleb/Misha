package com.bunch_of_keys.bunch.domain.bills;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cost_types")
public class CostType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String costType;

}
