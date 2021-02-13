package com.sample.ratingo.rest.service;

import com.sample.ratingo.api.ProductDO;
import com.sample.ratingo.mapper.ProductMapper;
import com.sample.ratingo.repository.Product;
import com.sample.ratingo.repository.ProductRepository;
import com.sample.ratingo.repository.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Persist a new product
     *
     * @param productDO
     * @return
     */
    public ProductDO createProduct(ProductDO productDO) {
        try {
            Product newProduct = ProductMapper.map(productDO);
            newProduct.setRating(new Rating());
            return ProductMapper.map(productRepository.save(newProduct));
        } catch (Exception e) {
            logger.error("Product Creation Failed : " + e.getMessage());
        }
        return null;
    }

    /**
     * Get product by Id
     *
     * @param id
     * @return
     */
    public ProductDO getProduct(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                return ProductMapper.map(product.get());
            }
        } catch (Exception e) {
            logger.info("Error while fetching product details : " + e.getMessage());
        }
        return null;
    }

    /**
     * Get all Products
     *
     * @return
     */
    public List<ProductDO> getAllProduct() {
        try {
            return productRepository.findAll().stream().map(ProductMapper::map).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error while fetching bulk product details : " + e.getMessage());
        }
        return new ArrayList<>();
    }


}
