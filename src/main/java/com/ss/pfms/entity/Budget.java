package com.ss.pfms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private double limitAmount;
    private int month;
    private int year;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}