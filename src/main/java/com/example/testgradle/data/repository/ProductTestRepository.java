package com.example.testgradle.data.repository;

import com.example.testgradle.data.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductTestRepository extends JpaRepository<Product, String> {

//    @Query("SELECT p FROM Product p WHERE p.price > 2000")
//    List<Product> findByPriceBasis();

    @Query(value = "SELECT p FROM Product p WHERE p.price > 2000")
    List<Product> testQueryMethod();

}
