package com.sample.ratingo.rest.service;

import com.sample.ratingo.api.RatingVO;
import com.sample.ratingo.repository.Product;
import com.sample.ratingo.repository.ProductRepository;
import com.sample.ratingo.repository.UserEnrollment;
import com.sample.ratingo.repository.UserEnrollmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<Long, Product> cachedProductMap = new HashMap<>();
    private final Map<Long, UserEnrollment> cachedEnrollmentMap = new HashMap<>();

    private final UserEnrollmentRepository userEnrollmentRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RatingService(final UserEnrollmentRepository userEnrollmentRepository,
                         final ProductRepository productRepository) {
        this.userEnrollmentRepository = userEnrollmentRepository;
        this.productRepository = productRepository;
        createLocalCache();
    }

    /**
     * Rate a Product and Calculates average rating
     *
     * @param ratingVO
     */
    public void rate(RatingVO ratingVO) {
        try {
            UserEnrollment userEnrollment = cachedEnrollmentMap.isEmpty() ?
                    userEnrollmentRepository.findById(ratingVO.getEnrollmentId()).get()
                    : cachedEnrollmentMap.get(ratingVO.getEnrollmentId());
            Product product = cachedProductMap.isEmpty() ?
                    productRepository.findById(userEnrollment.getProductId()).get()
                    : cachedProductMap.get(userEnrollment.getProductId());
            initUserEnrollment(userEnrollment);
            updateRating(product, ratingVO.getRatedValue());
            productRepository.save(product);
            userEnrollmentRepository.save(userEnrollment);
        } catch (Exception e) {
            logger.error("Error In rating Product : " + e.getMessage());
        }
    }

    /**
     * Initialize user enrollment
     *
     * @param userEnrollment
     */
    private void initUserEnrollment(UserEnrollment userEnrollment) {
        userEnrollment.setActive(false);
        userEnrollment.setEndDate(LocalDate.now(Clock.systemUTC()));
    }

    /**
     * Update rating based on user rating
     *
     * @param product
     * @param ratedValue
     */
    private void updateRating(Product product, long ratedValue) {
        product.getRating().setRatingCount(product.getRating().getRatingCount() + 1);
        product.getRating().setTotalRating(product.getRating().getTotalRating() + ratedValue);
        product.getRating().setAverageRating(BigDecimal.valueOf(product.getRating().getTotalRating())
                .divide(BigDecimal.valueOf(product.getRating().getRatingCount()), MathContext.DECIMAL32));
    }

    /**
     * Generate Local cache for faster fetching
     */
    private void createLocalCache() {
        if (cachedProductMap.isEmpty()) {
            try {
                cachedProductMap.putAll(productRepository.findAll().stream()
                        .collect(Collectors.toMap(Product::getId, product -> product)));
            } catch (Exception e) {
                logger.error("Unable to Generate Cache For Product : " + e.getMessage());
            }
        }
        if (cachedEnrollmentMap.isEmpty()) {
            try {
                cachedEnrollmentMap.putAll(userEnrollmentRepository.findAll().stream()
                        .collect(Collectors.toMap(UserEnrollment::getId, userEnrollment -> userEnrollment)));
            } catch (Exception e) {
                logger.error("Unable to Generate Cache for Enrollment : " + e.getMessage());
            }
        }
    }

}
