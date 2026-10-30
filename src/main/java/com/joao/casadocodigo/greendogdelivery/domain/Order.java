package com.joao.casadocodigo.greendogdelivery.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = true)
    private Client client;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Item> items;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @Min(1)
    private Double totalValue;
}
