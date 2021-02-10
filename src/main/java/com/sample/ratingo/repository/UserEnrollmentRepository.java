package com.sample.ratingo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEnrollmentRepository extends JpaRepository<UserEnrollment, Long> {

    Optional<UserEnrollment> findByUserIdAndProductId(Long userId, Long productId);

}
