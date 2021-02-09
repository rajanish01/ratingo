package com.sample.ratingo.rest.service;

import com.sample.ratingo.api.ProductDO;
import com.sample.ratingo.mapper.ProductMapper;
import com.sample.ratingo.repository.Product;
import com.sample.ratingo.repository.ProductRepository;
import com.sample.ratingo.repository.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDO createProduct(ProductDO productDO) {
        Product newProduct = ProductMapper.map(productDO);
        newProduct.setRating(new Rating());
        return ProductMapper.map(productRepository.save(newProduct));
    }


}
