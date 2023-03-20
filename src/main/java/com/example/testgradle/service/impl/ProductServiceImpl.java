package com.example.testgradle.service.impl;

import com.example.testgradle.data.dto.ProductDto;
import com.example.testgradle.data.entity.ProductEntity;
import com.example.testgradle.data.repository.ProductRepository;
import com.example.testgradle.mapping.ProductMapping;
import com.example.testgradle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductDto getProduct(String productId) {
        Optional<ProductEntity> res = productRepository.findById(productId);
        if(res.isEmpty())   { throw new IllegalArgumentException(String.format("ProductId %s가 존재하지 않습니다.", productId)); }

        return ProductMapping.convertToDto(res.get());
    }

    @Override
    public ProductDto creatProduct(ProductDto productDto) {
        ProductEntity res = ProductMapping.convertToModel(productDto);
        productRepository.save(res);
        Optional<ProductEntity> product = productRepository.findById(productDto.getProductId());
        if(product.isEmpty())   { throw new IllegalArgumentException(); }

        return ProductMapping.convertToDto(product.get());
    }


}
