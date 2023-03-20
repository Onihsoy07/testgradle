package com.example.testgradle.controller;

import com.example.testgradle.data.dto.ProductDto;
import com.example.testgradle.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable final String productId) {
        return new ResponseEntity<>(productServiceImpl.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> creatProduct(@Valid @RequestBody final ProductDto productDto) {
        ProductDto res = productServiceImpl.creatProduct(productDto);

        LOGGER.info(
                "[createProduct] Response >> productId : {}, priductName : {}, productPrice : {}, productStock : {}",
                res.getProductId(), res.getProductName(), res.getProductPrice(), res.getProductStock());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
