package com.sample.ratingo.mapper;

import com.sample.ratingo.api.RatingDO;
import com.sample.ratingo.repository.Rating;

public class RatingMapper {

    public static RatingDO map(Rating rating){
        RatingDO ratingDO = new RatingDO();
        ratingDO.setId(rating.getId());
        ratingDO.setRatingCount(rating.getRatingCount());
        ratingDO.setTotalRating(rating.getTotalRating());
        ratingDO.setAverageRating(rating.getAverageRating());
        return ratingDO;
    }

}
