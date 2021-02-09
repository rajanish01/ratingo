package com.sample.ratingo.rest.service;

import com.sample.ratingo.api.RatingVO;
import com.sample.ratingo.repository.Product;
import com.sample.ratingo.repository.ProductRepository;
import com.sample.ratingo.repository.UserEnrollment;
import com.sample.ratingo.repository.UserEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Clock;
import java.time.LocalDate;

@Service
public class RatingService {

    private final UserEnrollmentRepository userEnrollmentRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RatingService(final UserEnrollmentRepository userEnrollmentRepository,
                         final ProductRepository productRepository) {
        this.userEnrollmentRepository = userEnrollmentRepository;
        this.productRepository = productRepository;
    }

    public void rate(RatingVO ratingVO) {
        UserEnrollment userEnrollment = userEnrollmentRepository.findById(ratingVO.getEnrollmentId()).get();
        userEnrollment.setActive(false);
        userEnrollment.setEndDate(LocalDate.now(Clock.systemUTC()));
        Product product = productRepository.findById(userEnrollment.getProductId()).get();
        product.getRating().setRatingCount(product.getRating().getRatingCount() + 1);
        product.getRating().setTotalRating(product.getRating().getTotalRating() + ratingVO.getRatedValue());
        product.getRating().setAverageRating(BigDecimal.valueOf(product.getRating().getTotalRating())
                .divide(BigDecimal.valueOf(product.getRating().getRatingCount()), MathContext.DECIMAL32));
        productRepository.save(product);
        userEnrollmentRepository.save(userEnrollment);
    }

}
