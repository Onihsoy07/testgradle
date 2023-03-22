package com.example.testgradle.data.repository;


import com.example.testgradle.data.entity.ShortUrlEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {

    Optional<ShortUrlEntity> findByOrgUrl(String orgUrl);

}
