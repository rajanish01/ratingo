package com.sample.ratingo.mapper;

import com.sample.ratingo.api.ProductDO;
import com.sample.ratingo.repository.Product;

public class ProductMapper {

    public static ProductDO map(Product product) {
        ProductDO productDO = new ProductDO();
        productDO.setId(product.getId());
        productDO.setName(product.getName());
        productDO.setDescription(product.getDescription());
        return productDO;
    }

    public static Product map(ProductDO productDO) {
        Product product = new Product();
        product.setId(productDO.getId() == null ? product.getId() : productDO.getId());
        product.setName(productDO.getName());
        product.setDescription(productDO.getDescription());
        return product;
    }

}
