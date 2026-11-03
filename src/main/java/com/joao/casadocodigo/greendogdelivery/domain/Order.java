package com.joao.casadocodigo.greendogdelivery.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
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

    private String status;

    public Order() {}

    public Order(Client client, List<Item> items, Double totalValue) {
        super();
        this.client = client;
        this.items = items;
        this.totalValue = totalValue;
    }

    public Order(Long id, Client client, List<Item> items, Double totalValue) {
        super();
        this.id = id;
        this.client = client;
        this.items = items;
        this.totalValue = totalValue;
    }

    public Order(Long id, Client client, List<Item> items, Date date, Double totalValue, String status) {
        super();
        this.id = id;
        this.client = client;
        this.items = items;
        this.date = new Date();
        this.totalValue = totalValue;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", items=" + items +
                ", date=" + date +
                ", totalValue=" + totalValue +
                '}';
    }
}
