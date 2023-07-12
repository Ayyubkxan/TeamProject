package com.example.teamproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name","restaurant_id"})})
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String price;


    @ManyToOne
    private Restaurant restaurant;

}
