package com.example.testgradle.service;

import com.example.testgradle.data.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {

    public ProductDto getProduct(String productId);

    public ProductDto creatProduct(ProductDto productDto);
}
