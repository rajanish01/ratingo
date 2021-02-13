package com.sample.ratingo.rest.service;

import com.sample.ratingo.api.UserDO;
import com.sample.ratingo.api.UserEnrollmentVO;
import com.sample.ratingo.mapper.UserMapper;
import com.sample.ratingo.repository.User;
import com.sample.ratingo.repository.UserEnrollment;
import com.sample.ratingo.repository.UserEnrollmentRepository;
import com.sample.ratingo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final UserEnrollmentRepository userEnrollmentRepository;

    @Autowired
    public UserService(final UserRepository userRepository,
                       final UserEnrollmentRepository userEnrollmentRepository) {
        this.userRepository = userRepository;
        this.userEnrollmentRepository = userEnrollmentRepository;
    }

    /**
     * Persist New User
     *
     * @param userDO
     * @return
     */
    public UserDO createUser(UserDO userDO) {
        try {
            User user = UserMapper.map(userDO);
            return UserMapper.map(userRepository.save(user));
        } catch (Exception e) {
            logger.error("User Creation Failed : " + e.getMessage());
        }
        return null;
    }

    /**
     * Start New User Product Enrollment
     *
     * @param enrollmentVO
     * @return
     */
    public UserEnrollment startUserEnrollment(UserEnrollmentVO enrollmentVO) {
        try {
            UserEnrollment enrollment = new UserEnrollment();
            enrollment.setUserId(enrollmentVO.getUserId());
            enrollment.setProductId(enrollmentVO.getProductId());
            return userEnrollmentRepository.save(enrollment);
        } catch (Exception e) {
            logger.error("Unable To Start New Enrollment : " + e.getMessage());
        }
        return null;
    }

    /**
     * GET user enrollment details
     * @param id
     * @return
     */
    public UserEnrollment getUserEnrollment(Long id) {
        try {
            Optional<UserEnrollment> userEnrollment = userEnrollmentRepository.findById(id);
            return userEnrollment.orElse(null);
        } catch (Exception e) {
            logger.error("Unable to Fetch Enrollment Details : " + e.getMessage());
        }
        return null;
    }

}
