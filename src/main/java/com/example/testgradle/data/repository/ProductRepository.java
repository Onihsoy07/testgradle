package com.example.testgradle.data.repository;

import com.example.testgradle.data.entity.ProductEntity;
import com.example.testgradle.data.entity.ShortUrlEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {


}
