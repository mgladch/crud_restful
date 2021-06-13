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
@Table(name = "goods_tb")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String type;
    private String description;
    private int amount;
    private double price;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<Orders> orders = new HashSet<>();
}
