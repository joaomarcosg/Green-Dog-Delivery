package com.joao.casadocodigo.greendogdelivery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 30, message = "Length must be between {min} and {max} characters")
    private String name;

    @NotNull
    @Min(value = 20, message = "Minimum value must be R$ {value}")
    private Double price;
}
