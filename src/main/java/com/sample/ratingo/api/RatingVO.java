package com.sample.ratingo.api;

import lombok.Data;

@Data
public class RatingVO {

    private Long productId;

    private Long patientId;

    private Long ratedValue;

}
