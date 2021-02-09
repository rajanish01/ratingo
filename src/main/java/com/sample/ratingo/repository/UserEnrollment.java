package com.sample.ratingo.repository;

import com.sample.ratingo.util.LocalDateConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "user_enrollment")
public class UserEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "start_date")
    private LocalDate startDate = LocalDate.now(Clock.systemUTC());

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "active")
    private boolean active = true;

}
