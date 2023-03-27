package com.example.testgradle.repository;

import com.example.testgradle.data.entity.ProductEntity;
import com.example.testgradle.data.repository.ProductRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    private ProductEntity getProduct(String id, int nameNumber, int price, int stock) {
        return new ProductEntity(id, "상품" + nameNumber, price, stock);
    }

    @Test
    public void queryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceBasis();
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void nativeQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceBasisNativeQuery();
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameter(2000);
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterNamingQueryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming(2000);
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterNamingQueryTest2() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming2(2000);
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void nativeQueryPagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterPaging(2000,
            PageRequest.of(2, 2));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void basicCRUDTest() {
        /* create */
        // given
        ProductEntity product = ProductEntity.builder()
            .productId("testProduct")
            .productName("testP")
            .productPrice(1000)
            .productStock(500)
            .build();

        // when
        ProductEntity savedEntity = productRepository.save(product);

        // then
        Assertions.assertThat(savedEntity.getProductId())
            .isEqualTo(product.getProductId());
        Assertions.assertThat(savedEntity.getProductName())
            .isEqualTo(product.getProductName());
        Assertions.assertThat(savedEntity.getProductPrice())
            .isEqualTo(product.getProductPrice());
        Assertions.assertThat(savedEntity.getProductStock())
            .isEqualTo(product.getProductStock());

        /* read */
        // when
        ProductEntity selectedEntity = productRepository.findById("testProduct")
            .orElseThrow(RuntimeException::new);

        // then
        Assertions.assertThat(selectedEntity.getProductId())
            .isEqualTo(product.getProductId());
        Assertions.assertThat(selectedEntity.getProductName())
            .isEqualTo(product.getProductName());
        Assertions.assertThat(selectedEntity.getProductPrice())
            .isEqualTo(product.getProductPrice());
        Assertions.assertThat(selectedEntity.getProductStock())
            .isEqualTo(product.getProductStock());
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