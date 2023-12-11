package com.ecommerce.backend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, updatable = false)
    private Long id;
    @Column(name="grand_total")
    private BigDecimal GrandTotal;

}
