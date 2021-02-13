package com.sample.ratingo.rest.controller;

import com.sample.ratingo.api.UserDO;
import com.sample.ratingo.api.UserEnrollmentVO;
import com.sample.ratingo.rest.service.UserService;
import com.sample.ratingo.rest.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;
    private final RequestValidator requestValidator;

    @Autowired
    public UserRestController(final UserService userService,
                              final RequestValidator requestValidator) {
        this.userService = userService;
        this.requestValidator = requestValidator;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDO userDO) {
        try {
            List<String> errorList = requestValidator.validateNewUser(userDO);
            if (!errorList.isEmpty()) {
                return ResponseEntity.badRequest().body(errorList);
            }
            return ResponseEntity.accepted().body(userService.createUser(userDO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/enroll")
    public ResponseEntity enrollUser(@RequestBody UserEnrollmentVO enrollmentVO) {
        try {
            List<String> errorList = requestValidator.validateEnrollment(enrollmentVO);
            if (!errorList.isEmpty()) {
                return ResponseEntity.badRequest().body(errorList);
            }
            return ResponseEntity.accepted().body(userService.startUserEnrollment(enrollmentVO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/enroll")
    public ResponseEntity getUserEnrollment(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.accepted().body(userService.getUserEnrollment(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
