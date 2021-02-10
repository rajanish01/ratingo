package com.sample.ratingo.repository;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_rating")
    private Long totalRating = 0L;

    @Column(name = "rating_count")
    private Long ratingCount = 0L;

    @Column(name = "average_rating")
    private BigDecimal averageRating = BigDecimal.ZERO;

}
