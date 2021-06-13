package com.hladchuk.internet_Shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_tb")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private double stat;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<Orders> orders = new HashSet<>();
}
