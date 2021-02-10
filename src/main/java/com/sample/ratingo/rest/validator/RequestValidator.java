package com.sample.ratingo.rest.validator;

import com.sample.ratingo.api.RatingVO;
import com.sample.ratingo.api.UserDO;
import com.sample.ratingo.api.UserEnrollmentVO;
import com.sample.ratingo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestValidator {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserEnrollmentRepository userEnrollmentRepository;

    @Autowired
    public RequestValidator(final UserRepository userRepository,
                            final ProductRepository productRepository,
                            final UserEnrollmentRepository userEnrollmentRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userEnrollmentRepository = userEnrollmentRepository;
    }

    List<String> errorList = new ArrayList<>();

    /**
     * New User Validator
     * @param userDO
     * @return
     */
    public List<String> validateNewUser(UserDO userDO) {
        errorList.clear();
        if (userDO.getFirstName() == null || userDO.getFirstName().equals("")) {
            errorList.add("First Name Can Not Be Empty Or NULL !");
        }
        if (userDO.getLastName() == null || userDO.getLastName().equals("")) {
            errorList.add("Last Name Can Not Be Empty Or NULL !");
        }
        if (userDO.getEmail() == null || userDO.getEmail().equals("")) {
            errorList.add("Email Can Not Be Empty Or NULL !");
        }
        if (userDO.getMobNo() == null || userDO.getMobNo().equals("")) {
            errorList.add("Mobile No Can Not Be Empty Or NULL !");
        }
        Optional<User> user = userRepository.findByEmailOrMobNo(userDO.getEmail(), userDO.getMobNo());
        if (user.isPresent()) {
            errorList.add("User Already Registered, Please Try Different Email Or Phone No !");
        }
        return errorList;
    }

    /**
     * Validate New Enrollment
     * @param enrollmentVO
     * @return
     */
    public List<String> validateEnrollment(UserEnrollmentVO enrollmentVO) {
        errorList.clear();
        if (enrollmentVO.getProductId() == null || enrollmentVO.getUserId() == null) {
            errorList.add("Product Id Or User Id Can Not Be Null !");
        }
        Optional<User> user = userRepository.findById(enrollmentVO.getUserId());
        if (!user.isPresent()) {
            errorList.add("User Not Registered, Please Register Before Enrollment !");
        }
        Optional<Product> product = productRepository.findById(enrollmentVO.getProductId());
        if (!product.isPresent()) {
            errorList.add("Product Does Not Exist !");
        }
        Optional<UserEnrollment> userEnrollment = userEnrollmentRepository.findByUserIdAndProductId(
                enrollmentVO.getUserId(), enrollmentVO.getProductId());
        if (userEnrollment.isPresent() && userEnrollment.get().isActive()) {
            errorList.add("User Already Enrolled For Product ! " +
                    "Please End Previous For New Enrollment OR Choose Another Product !");
        }
        return errorList;
    }

    /**
     * Validate rating
     * @param ratingVO
     * @return
     */
    public List<String> validateRating(RatingVO ratingVO){
        errorList.clear();
        if(ratingVO.getEnrollmentId() == null){
            errorList.add("Enrollment Id Can Not Be Null !");
        }
        if(ratingVO.getRatedValue() == null
                || ratingVO.getRatedValue().compareTo(1L) < 0
                || ratingVO.getRatedValue().compareTo(5L) > 0){
            errorList.add("Please Rate Between 1-5 !");
        }
        Optional<UserEnrollment> enrollment = userEnrollmentRepository.findById(ratingVO.getEnrollmentId());
        if(!enrollment.isPresent()){
            errorList.add("Invalid Enrollment Id !");
            return errorList;
        }
        if(!enrollment.get().isActive()){
            errorList.add("Enrollment Is Over !");
        }
        return errorList;
    }

}
