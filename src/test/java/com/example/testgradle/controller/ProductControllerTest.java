package com.example.testgradle.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.testgradle.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.example.testgradle.data.dto.ProductDto;
import com.example.testgradle.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {

        String productId = "12315";

        given(productService.getProduct(productId)).willReturn(
            new ProductDto("15871", "pen", 5000, 2000));

        mockMvc.perform(
                get("/api/v1/product-api/product/" + productId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").exists())
            .andExpect(jsonPath("$.productName").exists())
            .andExpect(jsonPath("$.productPrice").exists())
            .andExpect(jsonPath("$.productStock").exists())
            .andDo(print());

        verify(productService).getProduct(productId);
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        given(productService.creatProduct(new ProductDto("15871", "pen", 5000, 1557))).willReturn(
            new ProductDto("15871", "pen", 5000, 1557));

        ProductDto productDto = ProductDto.builder().productId("15872").productName("pen").productPrice(5000).productStock(1557).build();
        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        String json = new ObjectMapper().writeValueAsString(productDto);

        mockMvc.perform(
                post("/api/v1/product-api/product")
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").exists())
            .andExpect(jsonPath("$.productName").exists())
            .andExpect(jsonPath("$.productPrice").exists())
            .andExpect(jsonPath("$.productStock").exists())
            .andDo(print());

        verify(productService).creatProduct(new ProductDto("15871", "pen", 5000, 1557));

    }

}