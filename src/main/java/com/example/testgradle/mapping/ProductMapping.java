package com.example.testgradle.mapping;

import com.example.testgradle.data.dto.ProductDto;
import com.example.testgradle.data.entity.ProductEntity;

public class ProductMapping {

    public static ProductDto convertToDto(ProductEntity product) {
        ProductDto productDto = new ProductDto().builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productStock(product.getProductStock())
                .build();
        return productDto;
    }

    public static ProductEntity convertToModel(ProductDto productDto) {
        ProductEntity product = new ProductEntity().builder()
                .productId(productDto.getProductId())
                .productName(productDto.getProductName())
                .productPrice(productDto.getProductPrice())
                .productStock(productDto.getProductStock())
                .build();
        return product;
    }




}
