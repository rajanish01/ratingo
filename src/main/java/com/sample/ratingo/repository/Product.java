package com.sample.ratingo.repository;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(targetEntity = Rating.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Rating rating;

}
