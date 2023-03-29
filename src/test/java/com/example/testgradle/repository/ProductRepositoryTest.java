package com.example.testgradle.repository;

import com.example.testgradle.data.entity.Product;
import com.example.testgradle.data.entity.ProductEntity;
import com.example.testgradle.data.repository.ProductRepository;
import com.example.testgradle.data.repository.ProductTestRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductTestRepository productRepository;

    private Product getProduct(String id, int nameNumber, int price, int stock) {
        return new Product(id, "상품" + nameNumber, price, stock);
    }

    @Test
    @Transactional
    public void queryTest1() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> res = productRepository.testQueryMethod();
        for (Product product : res) {
            System.out.println(product.toString());
        }

    }

    @BeforeEach
    void GenerateData() {
        int count = 1;
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 3000, 3000));
        productRepository.save(getProduct(Integer.toString(--count), count = count + 2, 1500, 200));
        productRepository.save(getProduct(Integer.toString(count), count++, 4000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1500));
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1000));
        productRepository.save(getProduct(Integer.toString(count), count++, 500, 10000));
        productRepository.save(getProduct(Integer.toString(count), count++, 8500, 3500));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count, 5100, 1700));
    }

}