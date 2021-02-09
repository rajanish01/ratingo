package com.sample.ratingo.rest.service;

import com.sample.ratingo.api.UserDO;
import com.sample.ratingo.api.UserEnrollmentVO;
import com.sample.ratingo.mapper.UserMapper;
import com.sample.ratingo.repository.User;
import com.sample.ratingo.repository.UserEnrollment;
import com.sample.ratingo.repository.UserEnrollmentRepository;
import com.sample.ratingo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserEnrollmentRepository userEnrollmentRepository;

    @Autowired
    public UserService(final UserRepository userRepository,
                       final UserEnrollmentRepository userEnrollmentRepository) {
        this.userRepository = userRepository;
        this.userEnrollmentRepository = userEnrollmentRepository;
    }

    public UserDO createUser(UserDO userDO) {
        User user = UserMapper.map(userDO);
        return UserMapper.map(userRepository.save(user));
    }

    public UserEnrollment startUserEnrollment(UserEnrollmentVO enrollmentVO) {
        UserEnrollment enrollment = new UserEnrollment();
        enrollment.setUserId(enrollmentVO.getUserId());
        enrollment.setProductId(enrollmentVO.getProductId());
        return userEnrollmentRepository.save(enrollment);
    }

    public void endUserEnrollment(Long enrollmentId) {
        UserEnrollment userEnrollment = userEnrollmentRepository.findById(enrollmentId).get();
        userEnrollment.setEndDate(LocalDate.now(Clock.systemUTC()));
        userEnrollment.setActive(false);
        userEnrollmentRepository.save(userEnrollment);
    }

}
