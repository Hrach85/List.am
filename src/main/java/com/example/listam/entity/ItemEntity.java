package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    @ManyToOne
    private CategoryEntity category;

}

