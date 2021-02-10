package com.sample.ratingo.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RatingDO {

    private Long id;

    private Long totalRating;

    private Long ratingCount;

    private BigDecimal averageRating;

}
