package com.example.testgradle.data.repository;

import com.example.testgradle.data.entity.ListenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListenerRepository extends JpaRepository<ListenerEntity, Long> {

}
