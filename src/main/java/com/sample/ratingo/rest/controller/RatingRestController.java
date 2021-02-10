package com.sample.ratingo.rest.controller;

import com.sample.ratingo.api.RatingVO;
import com.sample.ratingo.rest.service.RatingService;
import com.sample.ratingo.rest.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rate")
public class RatingRestController {

    private final RequestValidator requestValidator;
    private final RatingService ratingService;

    @Autowired
    public RatingRestController(final RequestValidator requestValidator,
                                final RatingService ratingService) {
        this.requestValidator = requestValidator;
        this.ratingService = ratingService;
    }

    @PutMapping
    public ResponseEntity rateProduct(@RequestBody RatingVO ratingVO) {
        try {
            List<String> errorList = requestValidator.validateRating(ratingVO);
            if (!errorList.isEmpty()) {
                return ResponseEntity.badRequest().body(errorList);
            }
            ratingService.rate(ratingVO);
            return ResponseEntity.accepted().body("Thanks For Valuable Feedback !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
