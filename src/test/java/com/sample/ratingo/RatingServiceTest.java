package com.sample.ratingo;

import com.sample.ratingo.api.ProductDO;
import com.sample.ratingo.repository.*;
import com.sample.ratingo.rest.service.ProductService;
import com.sample.ratingo.rest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatingServiceTest {

    private ProductService productService;
    private UserService userService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserEnrollmentRepository userEnrollmentRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
        userService = new UserService(userRepository, userEnrollmentRepository);
    }

    @Test
    public void testGetProductById() throws Exception {
        Product p = new Product();
        p.setId(1l);
        p.setRating(new Rating());
        p.getRating().setId(1l);
        when(productRepository.findById(1l)).thenReturn(java.util.Optional.of(p));
        ProductDO product = productService.getProduct(1L);
        verify(productRepository).findById(1L);

        assertEquals(1l, product.getId().longValue());
    }

    @Test
    public void testGetEnrollmentById() {
        UserEnrollment u = new UserEnrollment();
        u.setId(1l);
        when(userEnrollmentRepository.findById(1l)).thenReturn(java.util.Optional.of(u));
        UserEnrollment userEnrollment = userService.getUserEnrollment(1L);
        verify(userEnrollmentRepository).findById(1L);

        assertEquals(1l, userEnrollment.getId().longValue());
    }

    @Test
    public void testGetProductList() {
        List<Product> products = productRepository.findAll();
        assertThat(products.size(), is(greaterThanOrEqualTo(0)));
    }

}
