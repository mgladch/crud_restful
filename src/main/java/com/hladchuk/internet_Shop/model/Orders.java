package com.hladchuk.internet_Shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "order_tb")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "customer_id"), name = "customer_id")
    @JsonIgnoreProperties(value = {"order_tb", "hibernateLazyInitializer"})
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "goods_id"), name = "goods_id")
    @JsonIgnoreProperties(value = {"order_tb", "hibernateLazyInitializer"})
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "administrator_id"), name = "administrator_id")
    @JsonIgnoreProperties(value = {"order_tb", "hibernateLazyInitializer"})
    private Administrator administrator;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date")
    private Date orderDate;

}