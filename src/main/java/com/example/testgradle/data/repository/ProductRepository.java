package com.example.testgradle.data.repository;

import com.example.testgradle.data.entity.ProductEntity;
import java.util.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

//    @Query("SELECT p FROM ProductEntity p WHERE p.price > 2000")
//    List<ProductEntity> findByPriceBasis();
//
//    @Query(value = "SELECT * FROM ProductEntity p WHERE p.price > 2000", nativeQuery = true)
//    List<ProductEntity> findByPriceBasisNativeQuery();
//
//    @Query("SELECT p FROM ProductEntity p WHERE p.price > ?1")
//    List<ProductEntity> findByPriceWithParameter(Integer price);
//
//    @Query("SELECT p FROM ProductEntity p WHERE p.price > :price")
//    List<ProductEntity> findByPriceWithParameterNaming(Integer price);
//
//    @Query("SELECT p FROM ProductEntity p WHERE p.price > :pri")
//    List<ProductEntity> findByPriceWithParameterNaming2(@Param("pri") Integer price);
//
//    @Query(value = "SELECT * FROM ProductEntity WHERE price > :price",
//        countQuery = "SELECT count(*) FROM ProductEntity WHERE price > ?1",
//        nativeQuery = true)
//    List<ProductEntity> findByPriceWithParameterPaging(Integer price, Pageable pageable);


}
