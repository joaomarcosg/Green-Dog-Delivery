package com.joao.casadocodigo.greendogdelivery.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 30, message = "Length of the name must be between {min} and {max} characters")
    private String name;

    @NotNull
    @Length(min = 2, max = 300, message = "Length of address must be between {min} and {max} characters")
    private String address;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;
}
