package com.sample.ratingo.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "rating")
public class Rating {

    @Id
    @Column(name = "product_id")
    private long productId;

    @Column(name = "total_rating")
    private long totalRating;

    @Column(name = "rating_count")
    private long ratingCount;

    @Column(name = "average_rating")
    private BigDecimal averageRating;

}
